package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePropertyScreen extends JFrame{
    private JPanel panel;
    private JButton cancelButton;
    private JComboBox comboBox1;
    private JButton makeChangesButton;
    private JTextField newFee;

    public ChangePropertyScreen() {
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener {
        ChangePropertyScreen screen;

        public CancelActionListener(ChangePropertyScreen s){
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            screen.setVisible(false);
        }
    }

    public JButton getMakeChangesButton() {
        return makeChangesButton;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public JTextField getNewFee() {
        return newFee;
    }
}


