package persistence;

import model.Note;
import model.NoteBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    private NoteBook noteBook;

    @BeforeEach
    void runBefore(){
        noteBook = new NoteBook("lucy");
    }
    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyNoteBook() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyNoteBook.json");
            writer.open();
            writer.write(noteBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyNoteBook.json");
            noteBook = reader.read();
            assertEquals("lucy", noteBook.getStudentName());
            assertEquals("private", noteBook.getState());
            assertEquals(0, noteBook.getNotes().size());
            // expected
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralNoteBook() {
        try {
            noteBook.addNotes(new Note("Interface","CPSC-210", "interface is important."));
            noteBook.addNotes(new Note("Vectors","MATH-221", "Vectors has direction"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralNoteBook.json");
            writer.open();
            writer.write(noteBook);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralNoteBook.json");
            noteBook = reader.read();
            assertEquals("lucy", noteBook.getStudentName());
            assertEquals("private", noteBook.getState());
            ArrayList<Note> notes = noteBook.getNotes();
            assertEquals(2, notes.size());
            checkNote("Interface","CPSC-210", "interface is important.",notes.get(0));
            checkNote("Vectors","MATH-221", "Vectors has direction",notes.get(1));
            // expected

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
