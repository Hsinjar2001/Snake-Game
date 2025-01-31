import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class Signup extends JFrame {
    private JTextField firstNameField, lastNameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;

    public Signup() {
        setTitle("SignUp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use JLayeredPane to manage multiple layers of components
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 900, 700);
        setContentPane(layeredPane);

        // Set the background image for the signup screen
        ImageIcon backgroundImage = new ImageIcon("Snakeimg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 900, 700);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Create the SignUp panel with GridBagLayout
        JPanel SignupPanel = new JPanel(new GridBagLayout());
        SignupPanel.setOpaque(false);
        SignupPanel.setBounds(130, 50, 700, 500);
        layeredPane.add(SignupPanel, Integer.valueOf(1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title Label for Signup
        JLabel titleLabel = new JLabel("SIGN UP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        SignupPanel.add(titleLabel, gbc);

        // Input Fields for user details
        gbc.gridwidth = 1;
        addLabel(SignupPanel, "First Name:", 0, 1, gbc);
        firstNameField = addTextField(SignupPanel, 1, 1, gbc);

        addLabel(SignupPanel, "Last Name:", 0, 2, gbc);
        lastNameField = addTextField(SignupPanel, 1, 2, gbc);

        addLabel(SignupPanel, "Enter Email:", 0, 3, gbc);
        emailField = addTextField(SignupPanel, 1, 3, gbc);

        addLabel(SignupPanel, "Create Password:", 0, 4, gbc);
        passwordField = addPasswordField(SignupPanel, 1, 4, gbc);

        addLabel(SignupPanel, "Re-enter Password:", 0, 5, gbc);
        confirmPasswordField = addPasswordField(SignupPanel, 1, 5, gbc);

        // SignUp Button to save user details
        JButton signUpButton = new JButton("Sign Up");
        styleButton(signUpButton);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        SignupPanel.add(signUpButton, gbc);

        // Label for existing users to login
        JLabel alreadyAccountLabel = new JLabel("Already have an account?");
        alreadyAccountLabel.setFont(new Font("Arial", Font.BOLD, 17));
        alreadyAccountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        SignupPanel.add(alreadyAccountLabel, gbc);

        // Login Button to redirect users to Login screen
        JButton loginButton = new JButton("Login");
        styleButton(loginButton);
        gbc.gridy = 8;
        SignupPanel.add(loginButton, gbc);

        // Action Listener to open Login Screen
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginScreen loginForm = new LoginScreen();
                loginForm.setVisible(true);
                dispose(); // Close current Signup window
            }
        });

        // Action Listener to save user data
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSignupData();
            }
        });
    }

    // Method to add labels to the panel
    private void addLabel(JPanel panel, String text, int x, int y, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 21));
        label.setForeground(Color.WHITE);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(label, gbc);
    }

    // Method to add text fields to the panel
    private JTextField addTextField(JPanel panel, int x, int y, GridBagConstraints gbc) {
        JTextField textField = new JTextField(20);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(textField, gbc);
        return textField;
    }

    // Method to add password fields to the panel
    private JPasswordField addPasswordField(JPanel panel, int x, int y, GridBagConstraints gbc) {
        JPasswordField passwordField = new JPasswordField(20);
        gbc.gridx = x;
        gbc.gridy = y;
        panel.add(passwordField, gbc);
        return passwordField;
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 94, 93));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 4));
        button.setPreferredSize(new Dimension(120, 32));
    }

    // Method to save user data to a file
    private void saveSignupData() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        // Validate input fields
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save data to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("signup.txt", true))) {
            writer.write(firstName + ", " + lastName + ", " + email + ", " + password);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Signup Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Signup().setVisible(true));
    }
}
