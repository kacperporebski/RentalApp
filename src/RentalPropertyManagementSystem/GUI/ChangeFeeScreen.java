package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeFeeScreen extends JFrame{
    private JPanel panel;
    private JButton changeFeeButton;
    private JButton cancelButton;
    private JTextField newFee;
    private JTextPane oldFee;

    public ChangeFeeScreen(){
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener{
        ChangeFeeScreen screen;

        public CancelActionListener(ChangeFeeScreen s){
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            screen.setVisible(false);
        }
    }

    public JButton getChangeFeeButton() {
        return changeFeeButton;
    }

    public JTextField getNewFee() {
        return newFee;
    }
}
