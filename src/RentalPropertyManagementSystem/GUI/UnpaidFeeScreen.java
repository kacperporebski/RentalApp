package RentalPropertyManagementSystem.GUI;

import javax.swing.*;

public class UnpaidFeeScreen extends JFrame
{
    private JList unpaidFeesList;
    private JPanel panel1;
    private JScrollBar scrollBar1;
    private JButton refreshButton;

    private PayFeeScreen payFeeScreen;

    public UnpaidFeeScreen()
    {
        this.setName("Unpaid Fees");
        this.setContentPane(panel1);
        this.pack();
        payFeeScreen = new PayFeeScreen();
    }

    public JList getUnpaidFeesList()
    {
        return unpaidFeesList;
    }

    public void setUnpaidFeesList(JList unpaidFeesList)
    {
        this.unpaidFeesList = unpaidFeesList;
    }

    public JPanel getPanel1()
    {
        return panel1;
    }

    public void setPanel1(JPanel panel1)
    {
        this.panel1 = panel1;
    }

    public JScrollBar getScrollBar1()
    {
        return scrollBar1;
    }

    public void setScrollBar1(JScrollBar scrollBar1)
    {
        this.scrollBar1 = scrollBar1;
    }

    public JButton getRefreshButton()
    {
        return refreshButton;
    }

    public void setRefreshButton(JButton refreshButton)
    {
        this.refreshButton = refreshButton;
    }

    public PayFeeScreen getPayFeeScreen()
    {
        return payFeeScreen;
    }

    public void setPayFeeScreen(PayFeeScreen payFeeScreen)
    {
        this.payFeeScreen = payFeeScreen;
    }
}
