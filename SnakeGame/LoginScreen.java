import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("LOGIN"); // Set the title of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(900, 700); // Set the window size
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Prevent resizing

        // Use JLayeredPane to manage layers for background image and login panel
        JLayeredPane layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("Snakeimg.jpg"); // Ensure this image exists in the project directory
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 900, 700);
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Add background at lowest layer

        // Create the login panel
        JPanel loginPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for alignment
        loginPanel.setOpaque(false); // Make panel transparent to show background
        loginPanel.setBounds(250, 150, 400, 350); // Set position and size
        layeredPane.add(loginPanel, Integer.valueOf(1)); // Add login panel above background

        GridBagConstraints gbc = new GridBagConstraints(); // GridBagConstraints for layout management
        gbc.insets = new Insets(10, 10, 10, 0); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Set fill behavior

        // Title Label
        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40)); // Set font style and size
        titleLabel.setForeground(Color.WHITE); // Set text color
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(titleLabel, gbc); // Add title label to login panel

        // Email Label
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 18));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(emailLabel, gbc);

        // Email Input Field
        JTextField emailField = new JTextField(20);
        emailField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Add border
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(emailField, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordLabel, gbc);

        // Password Input Field
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(passwordField, gbc);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 94, 93));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(120, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, gbc);

        // No Account Label
        JLabel noAccountLabel = new JLabel("Don't have an account?");
        noAccountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        noAccountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(noAccountLabel, gbc);

        // Sign Up Button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBackground(new Color(0, 94, 93));
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        loginPanel.add(signUpButton, gbc);

        // Action Listener to open SignUp form
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Signup signupForm = new Signup(); // Create new Signup window
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
                    dispose(); // Close login window
                    new HomePage().setVisible(true); // Redirect to HomePage
                } else {
                    JOptionPane.showMessageDialog(LoginScreen.this, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Method to validate login credentials by reading from signup.txt
    private boolean validateLogin(String email, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("signup.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(", "); // Split user data by comma
                String savedEmail = userData[2];  // Email stored at index 2
                String savedPassword = userData[3];  // Password stored at index 3

                if (savedEmail.equals(email) && savedPassword.equals(password)) {
                    return true;  // Login successful
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;  // Login failed
    }

    // Main method to launch the Login screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}
