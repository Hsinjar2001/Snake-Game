import java.awt.*;
import javax.swing.*;

public class SignupForm extends JFrame {
    public SignupForm() {
        setTitle("SignUp");
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

        // Create the SignUp panel

        JPanel SignupPanel = new JPanel(new GridBagLayout());
        SignupPanel.setOpaque(false); // Make the panel transparent
        SignupPanel.setBounds(130, 50, 700, 500); // Center the panel on the screen
        layeredPane.add(SignupPanel, Integer.valueOf(1)); // Add to the foreground layer

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("SIGN UP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        
        SignupPanel.add(titleLabel, gbc);
        
        
        // First Name Label and First Name Field


        JLabel FirstLabel = new JLabel("First Name :");
        FirstLabel.setFont(new Font("Arial", Font.BOLD, 21));
        FirstLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        SignupPanel.add(FirstLabel, gbc);

        JTextField FirstField = new JTextField(20);
        FirstField.setBackground(new Color(255, 255, 255, 180)); // Slightly transparent white
        FirstField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 1;
        SignupPanel.add(FirstField, gbc);

        // Last Name Label and Field

        JLabel LastLabel = new JLabel("Last Name :");
        LastLabel.setFont(new Font("Arial", Font.BOLD, 21));
        LastLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        SignupPanel.add(LastLabel, gbc);

        JTextField LastField = new JTextField(20);
        LastField.setBackground(new Color(255, 255, 255, 180)); // Slightly transparent white
        LastField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 3;
        SignupPanel.add(LastField, gbc);

        // Emal  LAbel and Field


        JLabel emailLabel = new JLabel("Enter Email :");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 21));
        emailLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        SignupPanel.add(emailLabel, gbc);

        JTextField emailField = new JTextField(20);
        emailField.setBackground(new Color(255, 255, 255, 180)); // Slightly transparent white
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 5;
        SignupPanel.add(emailField, gbc);

        // Craete Password LAbel and field

        JLabel passwordLabel = new JLabel("Create Password :");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 21));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        SignupPanel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBackground(new Color(255, 255, 255, 180));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 7;
        SignupPanel.add(passwordField, gbc);

        // Re-enter Password Label and field

        JLabel repasswordLabel = new JLabel("Re-enter Password :");
        repasswordLabel.setFont(new Font("Arial", Font.BOLD, 21));
        repasswordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 9;
        SignupPanel.add(repasswordLabel, gbc);

        JPasswordField repasswordField = new JPasswordField(20);
        repasswordField.setBackground(new Color(255, 255, 255, 180));
        repasswordField.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 1));
        gbc.gridx = 1;
        gbc.gridy = 9;
        SignupPanel.add(repasswordField, gbc);


        // Login Button Creation

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0, 94, 93));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(BorderFactory.createLineBorder(new Color(0, 87, 90), 4));

        // Set the preferred size of the button

        loginButton.setPreferredSize(new Dimension(80, 30));

        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.gridwidth = 2;

        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        gbc.insets = new Insets(30, 40, 0, 0); // Add padding (top: 10, left: 10, bottom: 0, right: 0)


        SignupPanel.add(loginButton, gbc);

        // Already account text Label

        JLabel AlreadyAccountLabel = new JLabel("Already have an Account ?");
        AlreadyAccountLabel.setFont(new Font("Arial", Font.BOLD, 17));
        AlreadyAccountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 13;

        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        gbc.insets = new Insets(40, 140, 0, 0);

        SignupPanel.add(AlreadyAccountLabel, gbc);

        // SignUp button

        JButton signUpButton = new JButton("SignUp");
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setBackground(new Color(0, 94, 93));
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 1;

        signUpButton.setPreferredSize(new Dimension(120, 32));

        gbc.anchor = GridBagConstraints.WEST; // Align label to the left
        gbc.insets = new Insets(40, 230, 0, 0); // Add padding (top: 10, left: 10, bottom: 0, right: 0)

        SignupPanel.add(signUpButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupForm().setVisible(true));
    }
}

