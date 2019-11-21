package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenterListScreen extends JFrame{
    private JPanel panel;
    private JList renters;
    private JButton cancelButton;

    public RenterListScreen() {
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener {
       RenterListScreen screen;

        public CancelActionListener(RenterListScreen s) {
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            screen.setVisible(false);
        }
    }

    public JList getRenters() {
        return renters;
    }

    public void setRenters(JList renters) {
        this.renters = renters;
    }
}
