package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PropertiesScreen extends JFrame{
    private JPanel panel;
    private JList properties;
    private JButton cancelButton;

    public PropertiesScreen(){
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener {
        PropertiesScreen screen;

        public CancelActionListener(PropertiesScreen s){
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            screen.setVisible(false);
        }
    }

    public JList getProperties() {
        return properties;
    }
}
