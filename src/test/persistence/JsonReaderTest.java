package persistence;

import model.Note;
import model.NoteBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            NoteBook noteBook = reader.read();
            fail("IOException expected but no exception throw!");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyNoteBook() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyNoteBook.json");
        try {
            NoteBook noteBook = reader.read();
            assertEquals("lucy", noteBook.getStudentName());
            assertEquals("private", noteBook.getState());
            assertEquals(0, noteBook.getNotes().size());
            // expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralNoteBook() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralNoteBook.json");
        try {
            NoteBook noteBook = reader.read();
            assertEquals("lucy", noteBook.getStudentName());
            assertEquals("private", noteBook.getState());
            ArrayList<Note> notes = noteBook.getNotes();
            assertEquals(2, notes.size());
            checkNote("Interface","CPSC-210", "interface is important.",notes.get(0));
            checkNote("Vectors","MATH-221", "Vectors has direction",notes.get(1));
            // expected
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
