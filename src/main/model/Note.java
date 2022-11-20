package model;

/**
 * Represents a note that has a topic, course name,
 * and contents, notice that same topic of a course
 * can only summarize to one content
 * <p>
 * Each course with specific topic only have one corresponding content
 * that is, if one note has same course-name and in the same topic with
 * the other note then they must be the same note
 */

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Note implements Writable {
    private String topic;
    private String courseName;
    private String content;

    // EFFECTS: construct a note with given topic, course name and content
    // that you intend to write
    public Note(String topic, String course, String content) {
        this.topic = topic;
        this.courseName = course;
        this.content = content;
    }

    //MODIFIES: this
    //EFFECTS: modify current content
    public void changeContent(String change) {
        content = change;
    }

    // getters
    public String getTopic() {
        return topic;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getContent() {
        return content;
    }

    // EFFECTS: returns note as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("topic", topic);
        json.put("courseName", courseName);
        json.put("content", content);
        return json;
    }

    // EFFECTS: uses to compare two notes
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Note note = (Note) o;
        return Objects.equals(topic, note.topic) && Objects.equals(courseName, note.courseName);
    }

    // EFFECTS: uses to compare two notes
    @Override
    public int hashCode() {
        return Objects.hash(topic, courseName);
    }
}
