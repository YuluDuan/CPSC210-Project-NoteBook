package persistence;

import model.Note;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkNote(String topic, String courseName, String content, Note note) {
        assertEquals(topic,note.getTopic());
        assertEquals(courseName,note.getCourseName());
        assertEquals(content, note.getContent());
    }
}