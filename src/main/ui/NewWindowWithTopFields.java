package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents application's new window with two top fields of note:
 * course name and topic fields.
 */

public abstract class NewWindowWithTopFields extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 550;
    protected NoteBookAPP noteBookAPP;
    protected JTextField courseNameFiled;
    protected JTextField topicFiled;

    // EFFECTS: construct new window with two fields
    public NewWindowWithTopFields(String title, NoteBookAPP noteBookAPP) throws HeadlessException {
        super(title);
        this.noteBookAPP = noteBookAPP;
    }

    // MODIFIES: this
    // EFFECTS: initialize two fields with JTextField and JLabel, and add it to this window
    protected void initializeTwoTwoFiled() {
        JPanel panel1 = new JPanel();
        setLayout(new BorderLayout());

        JLabel courseNameL = new JLabel("Course Name");
        courseNameL.setLabelFor(courseNameFiled);
        panel1.add(courseNameL);

        courseNameFiled = new JTextField(20);
        panel1.add(courseNameFiled);

        JLabel topicL = new JLabel("Topic");
        topicL.setLabelFor(topicFiled);
        panel1.add(topicL);

        topicFiled = new JTextField(20);
        panel1.add(topicFiled);

        panel1.setBackground(Color.decode("#CFF4D2"));
        add(panel1, BorderLayout.PAGE_START);
    }
}
