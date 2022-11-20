package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a notebook that has student name, a list of notes,
// and the state of the notebook "public" or "private"

public class NoteBook implements Writable {
    private String studentName;
    private ArrayList<Note> notes;
    private String state;

    // EFFECTS: construct an NoteBook with no notes and assigned
    // student name, and private state applied
    public NoteBook(String name) {
        studentName = name;
        notes = new ArrayList<>();
        state = "private";

    }

    // MODIFIES: this
    // EFFECTS: add the note to NoteBook
    public void addNotes(Note note) {
        notes.add(note);
        EventLog.getInstance().logEvent(new Event("Course: " + note.getCourseName()
                + "; Topic: " + note.getTopic() + ", was added as new note."));
    }

    // REQUIRES: notebook cannot be empty
    // MODIFIES: this
    // EFFECTS: delete the notes from NoteBook
    public void deleteNotes(Note note) {
        notes.remove(note);
        EventLog.getInstance().logEvent(new Event("Course: " + note.getCourseName()
                + "; Topic: " + note.getTopic() + ", has removed."));
    }


    // setter
    // MODIFIES: this
    // EFFECTS: set the state to either "public notes" or "private notes"
    public void setState(String state) {
        this.state = state;
    }

    // MODIFIES: this
    // EFFECTS:  first find the note that you intend to change and then
    // change to the current contents, otherwise do nothing
    public void modifyNoteContents(Note note, String modification) {
        for (Note next : notes) {
            if (next.equals(note)) {
                next.changeContent(modification);
            }
        }

        EventLog.getInstance().logEvent(new Event("Content: " + note.getContent()
                + " has changed to " + modification + "."));
    }

    // EFFECTS: if this does not have the note in particular chapter of this course
    // returns "Sorry,can not find it"; otherwise return the content of this note
    public String findNotesByCourseAndTopic(String course, String topic) {
        EventLog.getInstance().logEvent(new Event("Searched a note of " + course + "."));
        for (Note next : notes) {
            if (next.getCourseName().equals(course) && next.getTopic().equals(topic)) {
                return next.getContent();
            }
        }
        return "Sorry,can not find it";
    }

    // REQUIRES: notes of this course are recorded by this notebook
    // EFFECTS:  get all notes of the specific course
    public ArrayList<Note> getCourseNotes(String course) {
        ArrayList<Note> courseNotes = new ArrayList<>();
        for (Note next : notes) {
            if (next.getCourseName().equals(course)) {
                courseNotes.add(next);
            }
        }
        return courseNotes;
    }

    //getters
    public ArrayList<Note> getNotes() {
        return notes;
    }

    public String getState() {
        return state;
    }

    public String getStudentName() {
        return studentName;
    }

    // EFFECTS: returns notebook as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("studentName", studentName);
        json.put("state", state);
        json.put("notes", notesToJson());
        return json;
    }

    // EFFECTS: returns notes in this notebook as a JSON array
    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Note n : notes) {
            jsonArray.put(n.toJson());
        }

        return jsonArray;
    }


}
