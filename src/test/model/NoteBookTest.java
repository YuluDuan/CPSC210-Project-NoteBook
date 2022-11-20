package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NoteBookTest {
    NoteBook noteBookTest;
    Note noteA;
    Note noteB;

    @BeforeEach
    void runBefore() {
        noteBookTest = new NoteBook("Lisa");
        noteA = new Note("Variance","STAT-302","the squared deviation from its mean");
        noteB = new Note("A5","CPSC-210","Interface only have method specification");
    }

    @Test
    void testConstructor() {
        ArrayList<Note> notes = noteBookTest.getNotes();
        assertTrue(notes.isEmpty());
        assertEquals("private", noteBookTest.getState());
        assertEquals("Lisa", noteBookTest.getStudentName());
    }

    @Test
    void addNoteTest() {
        noteBookTest.addNotes(noteA);
        ArrayList<Note> notes = noteBookTest.getNotes();
        assertEquals(noteA,notes.get(0));
        assertEquals(1,notes.size());

        noteBookTest.addNotes(noteB);
        assertEquals(noteB,notes.get(1));
        assertEquals(2,notes.size());
    }

    @Test
    void deleteNoteTest(){
        noteBookTest.addNotes(noteA);
        noteBookTest.addNotes(noteB);
        ArrayList<Note> notes = noteBookTest.getNotes();

        noteBookTest.deleteNotes(noteA);
        boolean b = false;
        for (Note next: notes){
            if (next.equals(noteA)) {
                b = true;
                break;
            }
        }
        assertFalse(b);
        assertEquals(1,notes.size());

        noteBookTest.deleteNotes(noteB);
        boolean b2 = false;
        for (Note next: notes){
            if (next.equals(noteB)) {
                b2 = true;
                break;
            }
        }
        assertFalse(b2);
        assertEquals(0,notes.size());
    }

    @Test
    void setStateTest (){
        noteBookTest.setState("public");
        assertEquals("public",noteBookTest.getState());
    }

    @Test
    void modifyNoteContentsTestFound(){
        noteBookTest.addNotes(noteA);
        noteBookTest.modifyNoteContents(noteA,"Variance will not be test in the exam :)");
        Note note = noteBookTest.getNotes().get(0);
        assertEquals("Variance will not be test in the exam :)", note.getContent());

        noteBookTest.modifyNoteContents(noteA,"no need for this course");
        assertEquals("no need for this course",note.getContent());
    }
    @Test
    void modifyNoteContentsTestNotFound(){
        noteBookTest.addNotes(noteA);
        noteBookTest.modifyNoteContents(noteB,"Variance will not be test in the exam :)");
        Note note = noteBookTest.getNotes().get(0);
        assertEquals("the squared deviation from its mean", note.getContent());
    }

    @Test
    void findNotesByCourseAndTopicTestEmpty(){
        String s = noteBookTest.findNotesByCourseAndTopic("CPSC-210","A5");
        assertEquals("Sorry,can not find it",s);
    }

    @Test
    void findNotesByCourseAndTopicTestNotTwo(){
        noteBookTest.addNotes(noteA);
        String s = noteBookTest.findNotesByCourseAndTopic("CPSC-210","A5");
        assertEquals("Sorry,can not find it",s);
    }

    @Test
    void findNotesByCourseAndTopicTestNotOne(){
        noteBookTest.addNotes(noteA);
        String s = noteBookTest.findNotesByCourseAndTopic("STAT-302","A5");
        assertEquals("Sorry,can not find it",s);
    }

    @Test
    void findNotesByCourseAndTopicTestFound(){
        noteBookTest.addNotes(noteB);
        String s = noteBookTest.findNotesByCourseAndTopic("CPSC-210","A5");
        assertEquals("Interface only have method specification",s);
    }

    @Test
    void getCourseNotesTestNotFound(){
        noteBookTest.addNotes(noteB);
        ArrayList<Note> notes = noteBookTest.getCourseNotes("STAT-302");
        assertTrue(notes.isEmpty());
    }

    @Test
    void getCourseNotesTestFound(){
        Note noteC = new Note("A4","CPSC-210","Interface can't create object");
        noteBookTest.addNotes(noteB);
        noteBookTest.addNotes(noteC);

        ArrayList<Note> notes = noteBookTest.getCourseNotes("CPSC-210");
        assertEquals(2,notes.size());
        assertEquals(noteB,notes.get(0));
        assertEquals(noteC,notes.get(1));
    }
}