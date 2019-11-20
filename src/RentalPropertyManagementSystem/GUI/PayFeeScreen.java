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

        payFeeButton.addActionListener(new PayFeeActionListener);
    }

    public class PayFeeActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
