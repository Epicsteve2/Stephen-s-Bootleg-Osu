/*Class for creating the display of the game*/
import javax.swing.*;
import java.awt.*;

public class Display {
    // Variables for creating and initalizing the display
    private JFrame frame;
    private Canvas canvas;
    private String title, name;
    private int width, height;

    public Display (String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    // Sets all of the conditions and properties of the display
        private void createDisplay () {
            frame = new JFrame (title);
            frame.setSize (width, height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(width, height));
            canvas.setMaximumSize(new Dimension(width, height));
            canvas.setMinimumSize(new Dimension(width, height));
            canvas.setFocusable(false);

            frame.add (canvas);
            frame.pack();
        }

    // Method for getting user to input a name if they get a high score.
    public void makeNewScore() {
        name = JOptionPane.showInputDialog(frame,"You got a High Score! Enter your name (no spaces):      ");
    }

    // Method for outputting a sad message if they don't beat a highscore
    public void makeSadScore() {
        JOptionPane.showMessageDialog(frame, "You didn't get a high score...");
    }

    // Method for getting their name
    public String getName () {
        return name;
    }

    // Method of getting the canvas to draw on
    public Canvas getCanvas() {
        return canvas;
    }
    public JFrame getFrame(){
        return frame;
    }
}
