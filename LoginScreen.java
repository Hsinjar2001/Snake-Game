import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use JLayeredPane to manage layers
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("Snakeimg.jpg"); // Ensure this image exists in the project directory
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 900, 700);
        layeredPane.add(backgroundLabel, Integer.valueOf(0));

        // Create the login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false);
        loginPanel.setBounds(250, 150, 400, 350);
        layeredPane.add(loginPanel, Integer.valueOf(1));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 94, 93));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(120, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        JLabel noAccountLabel = new JLabel("Don't have an account?");
        noAccountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        noAccountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(noAccountLabel, gbc);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(0, 94, 93));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(signUpButton, gbc);

        // Action Listener to save data to file
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open SignUp form
                Signup signupForm = new Signup();
                signupForm.setVisible(true);
                dispose();  // Close current Login window
            }
        });

        // Action Listener to authenticate login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredEmail = emailField.getText();
                String enteredPassword = new String(passwordField.getPassword());

                // Validate the login credentials
                if (validateLogin(enteredEmail, enteredPassword)) {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Close the login window and show the HomePage
                    dispose();
                    new HomePage().setVisible(true); // Redirect to HomePage
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateLogin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("signup.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(", ");
                String savedEmail = userData[2];  // Email is saved at index 2 in signup.txt
                String savedPassword = userData[3];  // Password is saved at index 3 in signup.txt

                if (savedEmail.equals(email) && savedPassword.equals(password)) {
                    return true;  // Login successful
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;  // Login failed
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}
