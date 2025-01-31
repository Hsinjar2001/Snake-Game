import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    // Constants for the game screen and snake's unit size
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
    static final int INITIAL_DELAY = 150; // Initial speed of the game
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6; // Initial body length of the snake
    int applesEaten; // Counter for apples eaten by the snake
    int appleX, appleY; // Coordinates of the apple
    char direction = 'R'; // Snake's initial direction
    boolean running = false; // Flag to track if the game is running
    Timer timer; // Timer object to control the game speed
    Random random; // Random object to generate random positions for the apple
    int currentDelay = INITIAL_DELAY; // Store the current delay (speed) of the game
    HomePage homePage; // Reference to the home page to update the highest score

    // Constructor to initialize the game panel
    GamePanel(HomePage homePage) {
        this.homePage = homePage;
        random = new Random(); // Initialize random generator
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black); // Set background color to black
        this.setFocusable(true); // Make the panel focusable for key events
        this.addKeyListener(new MyKeyAdapter()); // Add key listener to handle key presses
        startGame(); // Start the game when the panel is created
        this.requestFocusInWindow(); // Request focus for the panel
    }

    // Start the game
    public void startGame() {
        newApple(); // Generate a new apple
        running = true; // Set running flag to true
        currentDelay = INITIAL_DELAY; // Reset the game speed
        // Set initial snake position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 50 - i * UNIT_SIZE; // Initialize snake body at the start
            y[i] = 50;
        }
        timer = new Timer(currentDelay, this); // Initialize the timer
        timer.start(); // Start the game timer
    }

    // Game over function to display the game over screen
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40)); // Set font for score
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());

        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75)); // Set font for game over text
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over")) / 2, SCREEN_HEIGHT / 2);

        // Update highest score on the homepage
        homePage.updateHighestScore(applesEaten);
    }

    // Override paintComponent to update graphics
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g); // Draw the game elements
    }

    // Draw the game elements (apple, snake, score)
    public void draw(Graphics g) {
        if (running) {
            // Draw chessboard pattern
            for (int row = 0; row < SCREEN_HEIGHT / UNIT_SIZE; row++) {
                for (int col = 0; col < SCREEN_WIDTH / UNIT_SIZE; col++) {
                    if ((row + col) % 2 == 0) {
                        g.setColor(new Color(173, 255, 47)); // Light green
                    } else {
                        g.setColor(new Color(124, 252, 0)); // Darker green
                    }
                    g.fillRect(col * UNIT_SIZE, row * UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                }
            }

            // Draw apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // Draw snake
            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    // Snake head
                    g.setColor(Color.blue); // Snake head in blue
                    g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                    // Draw eyes on snake head
                    g.setColor(Color.white);
                    g.fillOval(x[i] + 6, y[i] + 6, 6, 6); // Left eye
                    g.fillOval(x[i] + 12, y[i] + 6, 6, 6); // Right eye

                    g.setColor(Color.black);
                    g.fillOval(x[i] + 8, y[i] + 8, 3, 3); // Left pupil
                    g.fillOval(x[i] + 14, y[i] + 8, 3, 3); // Right pupil

                } else {
                    // Snake body
                    g.setColor(new Color(0, 0, 255)); // Snake body in blue
                    g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

                    // Add shadow effect to body parts
                    g.setColor(new Color(0, 0, 139)); // Darker blue for shadow
                    g.fillOval(x[i] + 2, y[i] + 2, UNIT_SIZE - 4, UNIT_SIZE - 4);
                }
            }

            // Draw score on the screen
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 40));
            g.drawString("Score: " + applesEaten, 10, 30);

        } else {
            gameOver(g); // If the game is over, display the game over screen
        }
    }

    // Generate a new apple at a random location
    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    // Move the snake based on its current direction
    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Change head position based on the current direction
        switch (direction) {
            case 'U' -> y[0] -= UNIT_SIZE; // Up
            case 'D' -> y[0] += UNIT_SIZE; // Down
            case 'L' -> x[0] -= UNIT_SIZE; // Left
            case 'R' -> x[0] += UNIT_SIZE; // Right
        }
    }

    // Check if the snake eats an apple
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++; // Increase snake size
            applesEaten++; // Increase score
            newApple(); // Generate new apple
            increaseSpeed(); // Increase speed after eating apple
        }
    }

    // Check if the snake collides with itself or the screen borders
    public void checkCollisions() {
        // Check if snake collides with its body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false; // Game over if collision occurs
            }
        }

        // Check if snake collides with the screen borders
        if (x[0] < 0 || x[0] >= SCREEN_WIDTH || y[0] < 0 || y[0] >= SCREEN_HEIGHT) {
            running = false; // Game over if snake hits border
        }

        if (!running) {
            timer.stop(); // Stop the game timer if the game is over
        }
    }

    // Restart the game after game over
    public void restartGame() {
        applesEaten = 0;
        bodyParts = 6;
        direction = 'R';
        running = true;
        currentDelay = INITIAL_DELAY; // Reset speed
        // Reset snake's initial position
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 50 - i * UNIT_SIZE;
            y[i] = 50;
        }
        startGame(); // Start a new game
    }

    // Increase the game speed by decreasing the timer delay
    public void increaseSpeed() {
        // Gradually decrease delay, minimum delay is 50ms
        if (currentDelay > 50) {
            currentDelay -= 5; // Reduce delay by 5ms
            timer.setDelay(currentDelay); // Update timer delay
        }
    }

    // Key adapter to handle key events (player controls)
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> {
                    if (direction != 'R') direction = 'L'; // Prevent reverse direction
                }
                case KeyEvent.VK_RIGHT -> {
                    if (direction != 'L') direction = 'R'; // Prevent reverse direction
                }
                case KeyEvent.VK_UP -> {
                    if (direction != 'D') direction = 'U'; // Prevent reverse direction
                }
                case KeyEvent.VK_DOWN -> {
                    if (direction != 'U') direction = 'D'; // Prevent reverse direction
                }
                case KeyEvent.VK_R -> {
                    if (!running) {
                        restartGame(); // Restart the game when R is pressed
                    }
                }
            }
        }
    }

    // Action performed every time the timer ticks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move(); // Move the snake
            checkApple(); // Check if snake eats an apple
            checkCollisions(); // Check if snake collides with itself or borders
        }
        repaint(); // Repaint the screen after each action
    }
}
