package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A mouse listener for a JTable component.
 * @author www.codejava.neet
 * refernce: https://www.codejava.net/java-se/swing/a-simple-jtable-example-for-display
 */

public class TableMouseListener extends MouseAdapter {
    private JTable table;


    // EFFECTS: construct a TableMouseListener with given table
    public TableMouseListener(JTable notesOverview) {
        table = notesOverview;
    }



    /** EFFECTS:
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent event) {
        // selects the row at which point the mouse is clicked
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        table.setRowSelectionInterval(currentRow, currentRow);
    }

}
