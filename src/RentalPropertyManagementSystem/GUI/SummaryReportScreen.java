package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SummaryReportScreen extends JFrame {
    private JPanel panel;
    private JTextArea summaryInfo;
    private JButton cancelButton;
    private JList propertyList;
    private JScrollBar scrollBar1;

    public SummaryReportScreen(){
        this.setContentPane(panel);
        this.pack();

        cancelButton.addActionListener(new CancelActionListener(this));
    }

    public class CancelActionListener implements ActionListener{
        SummaryReportScreen screen;

        public CancelActionListener(SummaryReportScreen s){
            screen = s;
        }

        @Override
        public void actionPerformed(ActionEvent e){
            screen.setVisible(false);
        }
    }

    public JTextArea getSummaryInfo() {
        return summaryInfo;
    }

    public void setSummaryInfo(JTextArea summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    public JList getPropertyList() {
        return propertyList;
    }
}

