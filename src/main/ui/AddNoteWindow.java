package ui;

import model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's Add Note window frame.
 * initializeTextArea() method credit from
 * http://www.java2s.com/Code/Java/Swing-JFC/TextComponentsSamplerDemo.htm
 */

public class AddNoteWindow extends NewWindowWithTopFields {
    private JTextArea contentFiled;

    // EFFECTS: construct an add-note window with three fields of note and a confirm button
    public AddNoteWindow(NoteBookAPP noteBookAPP) {
        super("Let's Add New Note!", noteBookAPP);
        setSize(WIDTH,HEIGHT);
        initializeTwoTwoFiled();
        initializeTextArea();
        initializeConfirmButton();

        pack();
        setVisible(true);


    }

    // EFFECTS: initialize confirm button, add its action, and finally add it to this window
    private void initializeConfirmButton() {
        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        JButton confirm = new JButton("Confirm Adding");
        addNoteAction(confirm);
        p2.add(confirm);
        p2.setBackground(Color.decode("#CFF4D2"));
        add(p2,BorderLayout.PAGE_END);
    }


    // MODIFIES: this
    // EFFECTS: add note performed action, represents action to be taken when user wants
    //          to add a new note to the system.
    private void addNoteAction(JButton confirm) {
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Note note = new Note(topicFiled.getText(),courseNameFiled.getText(),contentFiled.getText());
                noteBookAPP.getNoteBook().addNotes(note);
            }
        });

    }


    // MODIFIES: this
    // EFFECTS: initialize content TextArea with empty text and proper font, size, scroll panel
    //          and finally added to the window
    private void initializeTextArea() {
        contentFiled = new JTextArea();
        contentFiled.setFont(new Font(
                "Serif", Font.ITALIC, 16));
        contentFiled.setLineWrap(true);
        contentFiled.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(contentFiled);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(550, 450));
        areaScrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Note Content"),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)),
                areaScrollPane.getBorder()));

        areaScrollPane.setBackground(Color.decode("#CFF4D2"));

        add(areaScrollPane, BorderLayout.CENTER);
        
    }

}
