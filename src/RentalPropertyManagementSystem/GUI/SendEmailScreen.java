package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendEmailScreen extends JFrame{
    private JPanel panel1;
    private JTextField sender;
    private JTextField message;
    private JButton sendButton;

    public SendEmailScreen() {
        this.setContentPane(panel1);
        this.pack();

        sendButton.addActionListener(new SendEmailActionListener());
    }

    public class SendEmailActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //email is sent through the email website, not through the rental app
           // panel1.setVisible(false);
        }
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JTextField getSender() {
        return sender;
    }

    public JTextField getMessage() {
        return message;
    }
}
