package RentalPropertyManagementSystem.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUserScreen extends JFrame
{
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JTextField emailTextField;
    private JComboBox accountTypeBox;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel panel;

    private InvalidRegisterScreen error;
    private UserAlreadyExistsScreen userExists;


    public RegisterUserScreen()
    {
        this.setName("Register");
        this.setContentPane(panel);
        this.pack();

        error = new InvalidRegisterScreen();
        userExists = new UserAlreadyExistsScreen();

        cancelButton.addActionListener(new CancelButtonActionListener());
    }

    public class CancelButtonActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            firstNameTextField.setText("");
            lastNameTextField.setText("");
            emailTextField.setText("");
            accountTypeBox.setSelectedIndex(0);
            usernameTextField.setText("");
            passwordTextField.setText("");

            setVisible(false);
        }
    }

    public JTextField getFirstNameTextField()
    {
        return firstNameTextField;
    }

    public JTextField getLastNameTextField()
    {
        return lastNameTextField;
    }

    public JLabel getLastNameLabel()
    {
        return lastNameLabel;
    }

    public JLabel getFirstNameLabel()
    {
        return firstNameLabel;
    }

    public JTextField getEmailTextField()
    {
        return emailTextField;
    }

    public JComboBox getAccountTypeBox()
    {
        return accountTypeBox;
    }

    public JTextField getUsernameTextField()
    {
        return usernameTextField;
    }

    public JTextField getPasswordTextField()
    {
        return passwordTextField;
    }

    public JButton getRegisterButton()
    {
        return registerButton;
    }

    public JButton getCancelButton()
    {
        return cancelButton;
    }

    public void setFirstNameTextField(JTextField firstNameTextField)
    {
        this.firstNameTextField = firstNameTextField;
    }

    public void setLastNameTextField(JTextField lastNameTextField)
    {
        this.lastNameTextField = lastNameTextField;
    }

    public void setLastNameLabel(JLabel lastNameLabel)
    {
        this.lastNameLabel = lastNameLabel;
    }

    public void setFirstNameLabel(JLabel firstNameLabel)
    {
        this.firstNameLabel = firstNameLabel;
    }

    public void setEmailTextField(JTextField emailTextField)
    {
        this.emailTextField = emailTextField;
    }

    public void setAccountTypeBox(JComboBox accountTypeBox)
    {
        this.accountTypeBox = accountTypeBox;
    }

    public void setUsernameTextField(JTextField usernameTextField)
    {
        this.usernameTextField = usernameTextField;
    }

    public void setPasswordTextField(JTextField passwordTextField)
    {
        this.passwordTextField = passwordTextField;
    }

    public void setRegisterButton(JButton registerButton)
    {
        this.registerButton = registerButton;
    }

    public void setCancelButton(JButton cancelButton)
    {
        this.cancelButton = cancelButton;
    }

    public void setPanel(JPanel panel)
    {
        this.panel = panel;
    }

    public InvalidRegisterScreen getError() {
        return error;
    }

    public UserAlreadyExistsScreen getUserExists() {
        return userExists;
    }
}
