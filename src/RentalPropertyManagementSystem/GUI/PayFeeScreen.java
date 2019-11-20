package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayFeeScreen extends JFrame{
    private JTextArea textArea1;
    private JButton payFeeButton;
    private JPanel panel;

    public PayFeeScreen(){
        this.setContentPane(panel);
        this.pack();

        payFeeButton.addActionListener(new PayFeeActionListener());
    }

    public class PayFeeActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public JTextArea getTextArea1()
    {
        return textArea1;
    }

    public void setTextArea1(JTextArea textArea1)
    {
        this.textArea1 = textArea1;
    }

    public JButton getPayFeeButton()
    {
        return payFeeButton;
    }

    public void setPayFeeButton(JButton payFeeButton)
    {
        this.payFeeButton = payFeeButton;
    }

    public JPanel getPanel()
    {
        return panel;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }
}
