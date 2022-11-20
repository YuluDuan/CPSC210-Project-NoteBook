package persistence;

import model.Event;
import model.EventLog;
import model.Note;
import model.NoteBook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Method taken from JSONReader class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads notebook from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads notebook from file and returns it;
    // throws IOException if an error occurs reading data from file
    public NoteBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Read notebook from a file"));
        return parseNoteBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses notebook from JSON object and returns it
    private NoteBook parseNoteBook(JSONObject jsonObject) {
        String studentName = jsonObject.getString("studentName");
        String state = jsonObject.getString("state");
        NoteBook noteBook = new NoteBook(studentName);
        noteBook.setState(state);
        addNotes(noteBook, jsonObject);
        return noteBook;
    }

    // MODIFIES: noteBook
    // EFFECTS: parses notes from JSON object and adds them to notebook
    private void addNotes(NoteBook noteBook, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("notes");
        for (Object json : jsonArray) {
            JSONObject nextNote = (JSONObject) json;
            addNote(noteBook, nextNote);
        }
    }

    // MODIFIES: noteBook
    // EFFECTS: parses note from JSON object and adds it to notebook
    private void addNote(NoteBook noteBook, JSONObject jsonObject) {
        String topic = jsonObject.getString("topic");
        String courseName = jsonObject.getString("courseName");
        String content = jsonObject.getString("content");
        Note note = new Note(topic, courseName, content);
        noteBook.addNotes(note);
    }
}