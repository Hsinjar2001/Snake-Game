// import javax.swing.JFrame;

// public class GameFrame extends JFrame {
    
//     GameFrame() {
//         this.add(new GamePanel());/
//         this.setTitle("Snake");
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setResizable(false);
//         this.pack();
//         this.setVisible(true);
//         this.setLocationRelativeTo(null);
//     }
    
// }




// import javax.swing.JFrame;

// public class GameFrame extends JFrame {

//     GameFrame() {
//         this.add(new GamePanel());
//         this.setTitle("Snake");
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setResizable(true);
//         this.setSize(600, 600);
//         this.setLocationRelativeTo(null);

//         this.add(new HomePage(this));
//         this.setVisible(true);
//     }
// }

import javax.swing.JFrame;

public class GameFrame extends JFrame {

    // Constructor to set up the game frame
    GameFrame() {
        this.setTitle("Snake"); // Set the title of the window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the game exits when the window is closed
        this.setResizable(false); // Prevent resizing of the window for consistent game experience
        this.setSize(600, 600); // Set the size of the window (600x600 pixels)
        this.setLocationRelativeTo(null); // Center the window on the screen

        // Add HomePage panel to the frame, passing this frame to the HomePage constructor
        this.add(new HomePage(this));
        
        this.setVisible(true); // Make the frame visible
    }
}
