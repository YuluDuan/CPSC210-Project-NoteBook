package ui;

import model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's Modify Note window frame.
 * initializeTextArea() method credit from
 * http://www.java2s.com/Code/Java/Swing-JFC/TextComponentsSamplerDemo.htm
 */

public class ModifyWindow extends JFrame {

    private JTextArea contentFiled;
    private Note note;
    private String initialContent;
    private NoteBookAPP noteBookAPP;

    // EFFECTS: construct a modify-note window with content textArea , and create a confirm button
    public ModifyWindow(Note note, String content, NoteBookAPP noteBookAPP) {
        super("Modify Content...");
        setSize(WIDTH, HEIGHT);

        this.note = note;
        initialContent = content;
        this.noteBookAPP = noteBookAPP;


        initializeTextArea();
        initializeConfirmButton();

        pack();
        setVisible(true);

    }

    // EFFECTS: initialize confirm button, add its action, and finally add it to this window
    private void initializeConfirmButton() {
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        JButton confirm = new JButton("Confirm Changing");
        p2.setBackground(Color.decode("#CFF4D2"));
        modifyNoteAction(confirm);

        p2.add(confirm);
        add(p2,BorderLayout.PAGE_END);
    }

    // MODIFIES: this
    // EFFECTS: modify-note performed action, represents action to be taken when user wants
    //          to modify a new note to the system.
    private void modifyNoteAction(JButton confirm) {
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                noteBookAPP.getNoteBook().modifyNoteContents(note,contentFiled.getText());
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initialize content TextArea with initial text and insert proper title,
    //          finally added it to this window
    private void initializeTextArea() {
        contentFiled = new JTextArea(initialContent);
        contentFiled.setFont(new Font(
                "Serif", Font.ITALIC, 16));
        contentFiled.setLineWrap(true);
        contentFiled.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(contentFiled);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(550, 450));
        areaScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Modify Note Content:"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                areaScrollPane.getBorder()));

        areaScrollPane.setBackground(Color.decode("#CFF4D2"));
        add(areaScrollPane, BorderLayout.CENTER);
    }
}
