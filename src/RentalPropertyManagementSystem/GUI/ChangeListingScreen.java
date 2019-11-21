package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeListingScreen extends JFrame
{
    private JPanel panel;
    private JButton changeStateButton;
    private JComboBox comboBox1;
    private JButton cancelButton;

    public ChangeListingScreen() {
        this.setName("Change Listing");
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener{
        ChangeListingScreen screen;

        public CancelActionListener(ChangeListingScreen s){
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            screen.setVisible(false);
        }
    }


    public JPanel getPanel()
    {
        return panel;
    }

    public JButton getChangeStateButton()
    {
        return changeStateButton;
    }

    public JComboBox getComboBox1()
    {
        return comboBox1;
    }

    public JButton getCancelButton()
    {
        return cancelButton;
    }
}
