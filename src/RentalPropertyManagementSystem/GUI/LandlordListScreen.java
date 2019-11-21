package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandlordListScreen extends JFrame{
    private JList landlords;
    private JButton cancelButton;
    private JPanel panel;

    public LandlordListScreen(){
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener {
        LandlordListScreen screen;

        public CancelActionListener(LandlordListScreen s) {
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            screen.setVisible(false);
        }
    }

    public JList getLandlords() {
        return landlords;
    }

}
