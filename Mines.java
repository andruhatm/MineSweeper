import src.Board;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Main class t
 */
public class Mines extends JFrame{
    //constant for width
    private final int WIDTH = 250;
    //constant for height
    private final int HEIGHT = 290;
    //initialize label for game info
    private JLabel statusbar;

    //public constructor runs from main() mthd when new instance of Class initializes
    public Mines() {

        //setPreferredSize(new Dimension(500, 500));

        //set window size
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        //closes window on exit from application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //sets title for window
        setTitle("Minesweeper");

        //initializes label with no text
        statusbar = new JLabel("");
        //adding label to frame
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar));

        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Mines();
    }
}
