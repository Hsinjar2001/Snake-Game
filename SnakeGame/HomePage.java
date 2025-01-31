import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private JLabel highestScoreLabel; // Label to display the highest score
    private int highestScore = 0; // Variable to store the highest score

    public HomePage() {
        this.setLayout(new BorderLayout()); // Set layout to BorderLayout
        this.setBackground(new Color(0, 123, 0)); // Set background color to a nice green shade

        // Title label for the game
        JLabel titleLabel = new JLabel("Snake Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 50)); // Set font for title
        titleLabel.setForeground(Color.red); // Set title color to red
        this.add(titleLabel, BorderLayout.NORTH); // Add title to the top of the panel

        // Highest score label to show the best score achieved
        highestScoreLabel = new JLabel("Highest Score: " + highestScore, JLabel.CENTER);
        highestScoreLabel.setFont(new Font("Ink Free", Font.BOLD, 30)); // Set font for highest score
        highestScoreLabel.setForeground(Color.black); // Set color of highest score text to black
        this.add(highestScoreLabel, BorderLayout.CENTER); // Add to the center of the panel

        // Start button to begin the game
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Ink Free", Font.BOLD, 30)); // Set font for start button
        startButton.setBackground(Color.white); // Set background color of button to white
        startButton.setForeground(Color.black); // Set text color of button to black

        // Action listener for the start button
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When start button is pressed, switch to the game panel
                getContentPane().removeAll(); // Remove all components from the frame
                GamePanel gamePanel = new GamePanel(HomePage.this); // Pass HomePage instance to GamePanel constructor
                add(gamePanel); // Add game panel to the frame
                revalidate(); // Revalidate frame to reflect changes
                repaint(); // Repaint frame to update the display
                gamePanel.requestFocusInWindow(); // Request focus for the game panel to capture key events
            }
        });
        this.add(startButton, BorderLayout.SOUTH); // Add start button to the bottom of the panel

        // Basic frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 700);
        this.setLocationRelativeTo(null); // Center the window
        this.setResizable(false);
    }

    // Method to update the highest score if the new score is higher
    public void updateHighestScore(int score) {
        if (score > highestScore) { // If new score is higher than the current highest score
            highestScore = score; // Update the highest score
            highestScoreLabel.setText("Highest Score: " + highestScore); // Update the label with new highest score
        }
    }

    // Main method for testing HomePage as a standalone component
    public static void main(String[] args) {
        HomePage homePage = new HomePage(); // Create HomePage instance
        homePage.setVisible(true); // Make the frame visible
    }
}
