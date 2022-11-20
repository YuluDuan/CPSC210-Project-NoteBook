package ui;

import javax.swing.*;
import java.awt.*;

/** represent welcome window
 * reference to https://www.youtube.com/watch?v=cwOFlsjVV1E
 */

public class WelcomeWindow extends JFrame {
    private String userName;

    // EFFECTS: construct welcome window with user's name
    public WelcomeWindow(String text) {
        super("Welcome!");
        userName = text;
        setSize(820, 553);
        setLayout(null);
        initializeJLabel();

        setVisible(true);

    }

    // EFFECTS: initialize all the components in this window
    private void initializeJLabel() {
        JLabel noteBook = new JLabel("NOTEBOOK");
        noteBook.setFont(new Font("Arial Black", Font.ITALIC, 80));
        noteBook.setHorizontalAlignment(SwingConstants.CENTER);
        noteBook.setForeground(new Color(255, 255, 255));
        noteBook.setBounds(8, -19, 675, 200);
        add(noteBook);

        JLabel welcome = new JLabel("Hi " + userName + ", Let's get started! ");
        welcome.setForeground(new Color(255,255,255));
        welcome.setFont(new Font("Arial",Font.BOLD,25));
        welcome.setBounds(10,130,400,200);
        add(welcome);


        JLabel backGroundImg = new JLabel("");
        backGroundImg.setIcon(new ImageIcon("src/picture/800*533.png"));
        backGroundImg.setBounds(0, 0, 800, 533);
        add(backGroundImg);
    }
}
