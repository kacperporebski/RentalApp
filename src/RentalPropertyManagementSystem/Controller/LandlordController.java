package RentalPropertyManagementSystem.Controller;

import RentalPropertyManagementSystem.Client.Container.*;
import RentalPropertyManagementSystem.Client.RenterWebsite;
import RentalPropertyManagementSystem.GUI.GUI;
import RentalPropertyManagementSystem.GUI.RegisterPropertyScreen;
import RentalPropertyManagementSystem.Users.AccountHolder;
import RentalPropertyManagementSystem.Users.Landlord;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LandlordController extends Controller
{

    public LandlordController(GUI view, RenterWebsite website)
    {
        super(view, website);

        view.getLandlordScreen().getLogoutButton().addActionListener(new LogoutActionListener());
        view.getLandlordScreen().getRegPropertyScreen().getRegisterPropertyButton().addActionListener(new RegisterProperty());
        view.getLandlordScreen().getRefreshButton().addActionListener(new DisplayLandlordProperties());
        view.getLandlordScreen().getUnpaidFeeScreen().getRefreshButton().addActionListener(new DisplayUnpaidFees());
        view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().addMouseListener(new PayUnpaidFees());
        view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().getPayFeeButton().addActionListener(new PayFeeButtonListener());
        view.getLandlordScreen().getPropertyList().addMouseListener(new DoubleClickEditProperty());
    }


    public class DisplayLandlordProperties implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<Property> properties = renterWebsite.propertyRepo.getLandlordProperties((Landlord)currentUser.get());
            displayProperties(view.getLandlordScreen().getPropertyList(), properties);
        }
    }

    public class PayUnpaidFees extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(e.getClickCount() == 2)
            {
                ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
                int index = view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().getSelectedIndex();
                view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().setVisible(true);
                view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().getTextArea1().setText(unpaidProperties.get(index).getRegistrationFee().toString());
            }
        }
    }

    public class RegisterProperty implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            RegisterPropertyScreen currentScreen = view.getLandlordScreen().getRegPropertyScreen();
            Property p;
            try {
                PropertyType propertyType = PropertyType.valueOf(currentScreen.getPropertyTypeComboBox().getSelectedItem().toString());
                int bedrooms = 0;
                int bathrooms = 0;
                double rentalFee = Double.parseDouble(currentScreen.getCostTextField().getText());
                boolean furnished;
                // Address address = new Address(currentScreen.getAddressTextField().getText(),currentScreen.getAddressTextField().getText(),
                //   currentScreen.getAddressTextField().getText(), currentScreen.getAddressTextField().getText());
                Address address = new Address(currentScreen.getHouseNumTextField().getText(),currentScreen.getStreetTextField().getText(),
                        currentScreen.getCityTextField().getText(), currentScreen.getCityQuadrantComboBox().getSelectedItem().toString());

                try {
                    bedrooms = Integer.parseInt(currentScreen.getBedroomTextField().getText());
                    bathrooms = Integer.parseInt(currentScreen.getBathroomTextField().getText());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (currentScreen.getFurnishedComboBox().getSelectedItem().toString().compareTo("Furnished") == 0)
                    furnished = true;
                else
                    furnished = false;

                p = new Property((Landlord) currentUser.get(), address, bedrooms, bathrooms, furnished, new Fee(rentalFee), propertyType);
            }catch (Exception e){
                view.getLandlordScreen().getRegPropertyScreen().getError().setVisible(true);
                return;
            }
            renterWebsite.propertyRepo.addProperty(p);
            renterWebsite.getMyDatabase().getPropertyDatabase().addProperty(p);

            currentScreen.setVisible(false);
        }
    }

    public class DisplayUnpaidFees implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            refreshUnpaidFees();
        }
    }

    public void refreshUnpaidFees()
    {
        ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
        displayProperties(view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList(), unpaidProperties);
    }

    public class PayFeeButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            ArrayList<Property> unpaidProperties = renterWebsite.propertyRepo.getLandlordUnpaidProperties((Landlord)currentUser.get());
            int index = view.getLandlordScreen().getUnpaidFeeScreen().getUnpaidFeesList().getSelectedIndex();

            unpaidProperties.get(index).getRegistrationFee().setPaid(true);
            refreshUnpaidFees();

            view.getLandlordScreen().getUnpaidFeeScreen().getPayFeeScreen().setVisible(false);
        }
    }

    public class DoubleClickEditProperty extends MouseAdapter
    {
        public void mouseClicked(MouseEvent e)
        {
            if(e.getClickCount() == 2)
            {
                int index = view.getLandlordScreen().getPropertyList().getSelectedIndex();

                for(ActionListener t : view.getLandlordScreen().getChangePropertyScreen().getMakeChangesButton().getActionListeners()){
                    view.getLandlordScreen().getChangePropertyScreen().getMakeChangesButton().removeActionListener(t);
                }

                view.getLandlordScreen().getChangePropertyScreen().getMakeChangesButton().addActionListener(new ChangePropertyActionListener(index));
                view.getLandlordScreen().getChangePropertyScreen().setVisible(true);
            }
        }
    }

    public class ChangePropertyActionListener implements ActionListener{
        int index;

        public ChangePropertyActionListener(int i){
            index = i;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            STATE changeState = STATE.valueOf(view.getLandlordScreen().getChangePropertyScreen().getComboBox1().getSelectedItem().toString());
            renterWebsite.propertyRepo.getLandlordProperties(((Landlord)currentUser.get())).get(index).setState(changeState);

            Fee newFee;
            try {
                Double strNewFee = Double.parseDouble(view.getLandlordScreen().getChangePropertyScreen().getNewFee().getText());
                newFee = new Fee(strNewFee);
            } catch (NumberFormatException s){
                System.out.println("Invalid input");
                newFee = renterWebsite.propertyRepo.getLandlordProperties(((Landlord)currentUser.get())).get(index).getRent();
            }
            renterWebsite.propertyRepo.getLandlordProperties(((Landlord)currentUser.get())).get(index).setRent(newFee);

            displayProperties(view.getLandlordScreen().getPropertyList(), renterWebsite.propertyRepo.getLandlordProperties((Landlord)currentUser.get()));
            view.getLandlordScreen().setVisible(true);
            view.getLandlordScreen().getChangePropertyScreen().setVisible(false);
        }
    }


}
