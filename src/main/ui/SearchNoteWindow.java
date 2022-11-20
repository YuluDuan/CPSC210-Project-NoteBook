package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents application's search note window frame.
 */

public class SearchNoteWindow extends NewWindowWithTopFields {
    private JButton search;

    // EFFECTS: sets a search note window with two fields of note and a search button
    public SearchNoteWindow(NoteBookAPP noteBookAPP) {
        super("Search...", noteBookAPP);
        setSize(WIDTH,HEIGHT);
        initializeTwoTwoFiled();
        initializeJButton();

        pack();
        setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: sets the search button and its action, then add it to this window
    private void initializeJButton() {
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        search = new JButton("Search");
        searchAction(search);
        panel2.setBackground(Color.decode("#CFF4D2"));
        panel2.add(search,BorderLayout.PAGE_START);

        add(panel2,BorderLayout.PAGE_END);
    }

    // EFFECTS: search note action performed, and create popup result window
    private void searchAction(JButton search) {
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = noteBookAPP.getNoteBook().findNotesByCourseAndTopic(courseNameFiled.getText(),
                        topicFiled.getText());
                popUpMessage(result);

            }
        });
    }

    // EFFECTS: sets a popup search result window
    private void popUpMessage(String result) {
        JOptionPane.showMessageDialog(this,result,"Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
