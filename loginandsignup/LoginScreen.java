import java.awt.*;
import javax.swing.*;

public class LoginScreen extends JFrame {
    public LoginScreen() {
        setTitle("LOGIN");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        // Use JLayeredPane to manage layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 900, 700);
        setContentPane(layeredPane);

        // Set the background image
        ImageIcon backgroundImage = new ImageIcon("./Snakeimg.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, 900, 700); // Ensure it covers the whole frame
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Add to the background layer

        // Create the login panel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setOpaque(false); // Make the panel transparent
        loginPanel.setBounds(200, 100, 400, 300); // Center the panel on the screen
        layeredPane.add(loginPanel, Integer.valueOf(1)); // Add to the foreground layer

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        loginPanel.add(titleLabel, gbc);

        JLabel emailLabel = new JLabel("Email :");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 21));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        loginPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setBackground(new Color(255, 255, 255, 180)); // Slightly transparent white
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(emailField, gbc);

        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 21));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(255, 255, 255, 180));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        loginPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 94, 93));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 4));

        // Set the preferred size of the button

        loginButton.setPreferredSize(new Dimension(120, 30));

        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 2;

        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        gbc.insets = new Insets(30, 40, 0, 0); // Add padding (top: 10, left: 10, bottom: 0, right: 0)


        loginPanel.add(loginButton, gbc);


        JLabel NoAccountLabel = new JLabel("Don't have an Account ?");
        NoAccountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        NoAccountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 9;

        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        gbc.insets = new Insets(30, 20, 0, 0); // Add padding (top: 10, left: 10, bottom: 0, right: 0)

        loginPanel.add(NoAccountLabel, gbc);

        JButton signUpButton = new JButton("SignUp");
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(0, 94, 93));
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;

        signUpButton.setPreferredSize(new Dimension(80, 30));

        gbc.anchor = GridBagConstraints.CENTER; // Align label to the left
        gbc.insets = new Insets(30, 40, 0, 0); // Add padding (top: 10, left: 10, bottom: 0, right: 0)

        loginPanel.add(signUpButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginScreen().setVisible(true));
    }
}

