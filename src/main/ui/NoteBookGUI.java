package ui;

import model.Event;
import model.EventLog;
import model.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Represents application's main window frame.
 * reference : Alarm system project on cpsc-210 edx page
 * https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
 */

public class NoteBookGUI extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 550;
    private JTable notesOverview;
    private NoteBookAPP noteBookAPP;
    private EventLog eventLog;

    // EFFECTS: constructor sets up text field panel, menu, and note display window.
    public NoteBookGUI() {
        super("NoteBook");
        setLayout(new BorderLayout());
        //A border layout lays out a container, arranging and resizing its components to fit in five
        // regions: north, south, east, west, and center.
        setSize(WIDTH, HEIGHT);

        noteBookAPP = new NoteBookAPP();
        eventLog = EventLog.getInstance(); // !!!

        addTextFieldPane();
        addMenu();
        addNotesDisplayPane();

        setOnCloseAction();
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // EFFECTS: print event log when quit the program
    private void setOnCloseAction() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                for (Event next : eventLog) {
                    System.out.println(next.toString() + "\n");
                }
            }
        });
    }

    // EFFECTS: add user-name panel constructed by JTextField and JButton to main window
    // reference: https://docs.oracle.com/javase/tutorial/uiswing/components/list.html JList Demo
    public void addTextFieldPane() {
        JTextField textUserName = new JTextField(10);
        JLabel label = new JLabel("Enter your name:");

        JButton submitButton = new JButton("Submit");
        submitAction(submitButton, textUserName);


        label.setLabelFor(textUserName);


        JPanel topPane = new JPanel();
        topPane.setLayout(new BoxLayout(topPane,
                BoxLayout.LINE_AXIS));
        topPane.add(Box.createHorizontalStrut(5));
        topPane.add(new JSeparator(SwingConstants.VERTICAL));
        topPane.add(Box.createHorizontalStrut(5));
        topPane.add(label);
        topPane.add(textUserName);
        topPane.add(submitButton);
        topPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPane.setBackground(Color.decode("#CFF4D2"));
        getContentPane().add(topPane, BorderLayout.PAGE_START);
    }

    // EFFECTS: perform the submit action to create a popup window, and represents
    //          action to be taken when user wants click the submit button
    private void submitAction(JButton submitButton, JTextField textUserName) {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popUpMessage(textUserName.getText());

            }
        });

    }

    // EFFECTS: create the popup window with proper descriptions
    private void popUpMessage(String text) {
        new WelcomeWindow(text);

//        String s = "Hi " + text + ", welcome to the NoteBook Application, Let's get start! "
//                + "\n Do you need a visual guide for this NoteBook Application? ";

//        JOptionPane.showMessageDialog(this,s,"Welcome!",
//                JOptionPane.OK_OPTION, new ImageIcon("src/picture/note.png"));
    }

    // MODIFIES: this
    // EFFECTS: add note display panel, including a JTable with its label and an
    //          update button. Finally, adding this panel to the main window
    public void addNotesDisplayPane() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(null);

        JLabel label = new JLabel("Notes Overview");
        label.setBounds(20, 10, 100, 16);
        tablePanel.add(label);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(600, 435, 100, 15);
        tablePanel.add(updateButton);
        viewNotesAction(updateButton);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 30, 700, 400);
        tablePanel.add(scrollPane);
        tablePanel.setBackground(Color.decode("#CFF4D2"));

        notesOverview = new JTable();
        setInitialTable();
        scrollPane.setViewportView(notesOverview);

        this.getContentPane().add(tablePanel);
    }

    // MODIFIES: this
    // EFFECTS: perform the update action to reload the table when user click the
    //          update button
    private void viewNotesAction(JButton updateButton) {
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Note> notes = noteBookAPP.getNoteBook().getNotes();
                NotesOverviewTable model = new NotesOverviewTable(notes);
                notesOverview.setModel(model);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: set initial table and add the popup menu for the table
    public void setInitialTable() {
        ArrayList<Note> notes = noteBookAPP.getNoteBook().getNotes();
        NotesOverviewTable model = new NotesOverviewTable(notes);
        notesOverview.setModel(model);

        setPopUpMenuForTable();
    }

    // MODIFIES: this
    // EFFECTS: construct the popup menu to the table and their expected action
    // credit to https://www.codejava.net/java-se/swing/jtable-popup-menu-example
    private void setPopUpMenuForTable() {
        // constructs the popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItemRemove = new JMenuItem("Delete Current Note");
        JMenuItem menuItemModify = new JMenuItem("Modify Current Note");

        // add to the menu
        popupMenu.add(menuItemRemove);
        popupMenu.add(menuItemModify);

        // perform action
        removeAction(menuItemRemove);
        modifyAction(menuItemModify);

        // sets the popup menu for the table
        notesOverview.setComponentPopupMenu(popupMenu);
        //  make a row automatically selected when the user right-clicks on the table
        notesOverview.addMouseListener(new TableMouseListener(notesOverview));
    }

    // MODIFIES: this
    // EFFECTS: perform the modify-action when the user click the modify menu and create new window
    //          and reload the table
    private void modifyAction(JMenuItem menuItemModify) {
        menuItemModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = notesOverview.getSelectedRow();
                String courseName = (String) notesOverview.getValueAt(selectedRow, NotesOverviewTable.COURSE_NAME_COL);
                String topic = (String) notesOverview.getValueAt(selectedRow, NotesOverviewTable.TOPIC_COL);
                String content = (String) notesOverview.getValueAt(selectedRow, NotesOverviewTable.CONTENT_COL);
                Note note = new Note(topic, courseName, content);
                new ModifyWindow(note, content, noteBookAPP);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: perform the remove action when the remove menu item, and reload the table
    private void removeAction(JMenuItem menuItemRemove) {
        menuItemRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = notesOverview.getSelectedRow();
                String courseName = (String) notesOverview.getValueAt(selectedRow, NotesOverviewTable.COURSE_NAME_COL);
                String topic = (String) notesOverview.getValueAt(selectedRow, NotesOverviewTable.TOPIC_COL);
                String content = (String) notesOverview.getValueAt(selectedRow, NotesOverviewTable.CONTENT_COL);
                Note note = new Note(topic, courseName, content);
                noteBookAPP.getNoteBook().deleteNotes(note);

                ArrayList<Note> notes = noteBookAPP.getNoteBook().getNotes();
                NotesOverviewTable model2 = new NotesOverviewTable(notes);
                notesOverview.setModel(model2);
            }

        });
    }



    // EFFECTS: Adds menu bar.
    public void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic('F');
        addMenuItem(fileMenu, new SaveFileAction(),
                KeyStroke.getKeyStroke("control S"),
                new ImageIcon("src/picture/save-512.png"));

        addMenuItem(fileMenu, new LoadFileAction(),
                KeyStroke.getKeyStroke("control L"),
                new ImageIcon("src/picture/load.png"));

        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic('E');

        addMenuItem(editMenu, new AddNote(), null,
                new ImageIcon("src/picture/add_item.png"));
        menuBar.add(editMenu);

        JMenu toolMenu = new JMenu("Tool");
        toolMenu.setMnemonic('T');
        addMenuItem(toolMenu, new SearchAction(),
                KeyStroke.getKeyStroke("control S"),
                new ImageIcon("src/picture/search.png"));
        menuBar.add(toolMenu);

        setJMenuBar(menuBar);
    }


    /**
     * EFFECTS:
     * Adds an item with given handler to the given menu
     *
     * @param theMenu     menu to which new item is added
     * @param action      handler for new menu item
     * @param accelerator keystroke accelerator for this menu item
     */
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator, ImageIcon imageIcon) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        menuItem.setIcon(imageIcon);
        theMenu.add(menuItem);
    }

    /** EFFECTS:
     * Represents action to be taken when user wants to add new note
     * to the system.
     */
    private class AddNote extends AbstractAction {

        AddNote() {
            super("Add");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            new AddNoteWindow(noteBookAPP);
        }
    }

    /** EFFECTS:
     * Represents action to be taken when user wants to search a note
     * from the system.
     */
    private class SearchAction extends AbstractAction {

        SearchAction() {
            super("Search");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            new SearchNoteWindow(noteBookAPP);
        }
    }


    /**
     *
     * MODIFIES: this
     * EFFECTS:
     * Represents action to be taken when user wants to load file
     * to the system.
     */
    private class LoadFileAction extends AbstractAction {

        LoadFileAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            noteBookAPP.loadNoteBook();
            ArrayList<Note> notes = noteBookAPP.getNoteBook().getNotes();
            NotesOverviewTable model = new NotesOverviewTable(notes);
            notesOverview.setModel(model);
        }
    }

    /** EFFECTS:
     * Represents action to be taken when user wants to save file
     * to the system.
     */
    private class SaveFileAction extends AbstractAction {

        SaveFileAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            noteBookAPP.saveNoteBook();
        }
    }
}
