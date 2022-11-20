package ui;

import model.Note;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Represents Notes Overview Table on the main window.
 * Source: https://www.codejava.net/java-se/swing/6-techniques-for-sorting-jtable-you-should-know
 */
public class NotesOverviewTable extends AbstractTableModel {
    public static final int COURSE_NAME_COL = 0;
    public static final int TOPIC_COL = 1;
    public static final int CONTENT_COL = 2;

    private final String[] headers = {"Course Name", "Topic", "Content"};
    private ArrayList<Note> notes;

    // EFFECTS: constructs a table model with a set of notes
    public NotesOverviewTable(ArrayList<Note> notes) {
        this.notes = notes;

    }

    // getters
    @Override
    public int getRowCount() {
        return notes.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Note note = notes.get(row);
        switch (col) {
            case COURSE_NAME_COL:
                return note.getCourseName();
            case TOPIC_COL:
                return note.getTopic();
            case CONTENT_COL:
                return note.getContent();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }


    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}
