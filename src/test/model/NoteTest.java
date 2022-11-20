package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.Objects.hash;
import static org.junit.jupiter.api.Assertions.*;

public class NoteTest {
    Note noteTest;

    @BeforeEach
    void runBefore(){
        noteTest = new Note("Variance","STAT-302","Variance is not squared distance");
    }
    @Test
    void testConstructor(){
       assertEquals("Variance",noteTest.getTopic());
       assertEquals("STAT-302",noteTest.getCourseName());
       assertEquals("Variance is not squared distance",noteTest.getContent());
    }

    @Test
    void changeContentTest(){
        noteTest.changeContent("Variance is squared distance");
        assertEquals("Variance is squared distance",noteTest.getContent());

        noteTest.changeContent("Variance is squared distance from mean");
        assertEquals("Variance is squared distance from mean",noteTest.getContent());
    }

    @Test
    void equalsTest(){
        Note note = null;
        assertFalse(noteTest.equals(note));

        String s = "hi";
        assertFalse(noteTest.equals(s));

        // && case
        Note note2 = new Note("Variance","STAT-302","Variance is not squared distance");
        assertTrue(note2.equals(noteTest));

        // course
        Note note3 = new Note("Variance","STAT-305","Variance is not squared distance");
        assertFalse(note3.equals(noteTest));

        // topic
        Note note4 = new Note("Var","STAT-302","Variance is not squared distance");
        assertFalse(note4.equals(noteTest));

        Note note5 = new Note("Variance","STAT-302","Variance !!");
        assertTrue(note5.equals(noteTest));

    }

    @Test
    void hashCodeTest(){
        Note note = new Note("Variance","STAT-302","Variance is not squared distance");
        assertTrue(note.hashCode() == noteTest.hashCode());
    }
}
