package ui;

import model.Note;
import model.NoteBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
        * Represents console based user interface
        * Reference: Teller Application project provided on edx CPSC-210
        */

public class NoteBookAPP {
    private static final String JSON_STORE = "./data/NoteBook.json";
    private static Scanner input;
    private NoteBook noteBook;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the teller application
    public NoteBookAPP() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        noteBook = new NoteBook(System.getProperty("user.name"));
        // runNoteBook();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runNoteBook() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        // System.out.println("Please enter your name:");
        // String name = input.nextLine();
        // noteBook = new NoteBook(System.getProperty("user.name"));

        System.out.println("Hello, " + noteBook.getStudentName() + "! "
                + "Would you like to : ");

        boolean keepDoing = true;
        String command;

        while (keepDoing) {
            displayMenu();
            command = input.next(); //
            command = command.toLowerCase();

            if (command.equals("f")) {
                keepDoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nKeep going, you're almost there!");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add Notes");
        System.out.println("\tdelete -> delete Notes");
        System.out.println("\tchange -> modify Note Contents");
        System.out.println("\tset -> set State");
        System.out.println("\tsearch -> find Notes");
        System.out.println("\tprint -> print Course Review Sheet");
        System.out.println("\ts -> save notebook to file"); //
        System.out.println("\tl -> load notebook from file"); //
        System.out.println("\tf -> finish");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            addNote();
        } else if (command.equals("delete")) {
            deleteNote();
        } else if (command.equals("change")) {
            changeNote();
        } else if (command.equals("set")) {
            setTheState();
        } else if (command.equals("search")) {
            searchNote();
        } else if (command.equals("print")) {
            printCourseNotes();
        } else if (command.equals("s")) {
            saveNoteBook();
        } else if (command.equals("l")) {
            loadNoteBook();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts an add note behaviour
    private void addNote() {
        String courseName = inputCourseName();
        String topic = inputTopic(courseName, "add");
        String content = inputContent(courseName, topic);

        Note note1 = new Note(topic, courseName, content);
        noteBook.addNotes(note1); // call noteBook

        System.out.print("Successfully added!");
    }

    // MODIFIES: this
    // EFFECTS: conducts delete note behaviour
    private void deleteNote() {
        String courseName = inputCourseName();
        String topic = inputTopic(courseName, "delete");
        String result = noteBook.findNotesByCourseAndTopic(courseName, topic);
        if (result.equals("Sorry,can not find it")) {
            System.out.println(result);
        } else {
            ArrayList<Note> notes = noteBook.getNotes();
            int i = 0;
            while (notes.size() > 0) {
                Note note = notes.get(i);
                if (note.getContent().equals(result) && note.getTopic().equals(topic)
                        && note.getCourseName().equals(courseName)) {
                    noteBook.deleteNotes(note);
                    System.out.print("Successfully deleted!");
                    break;
                }
                i++;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts modify notes behaviour
    private void changeNote() {
        String courseName = inputCourseName();
        String topic = inputTopic(courseName, "modify");
        System.out.println("Modify to?");
        String changeTo = input.nextLine();

        String result = noteBook.findNotesByCourseAndTopic(courseName, topic);
        if (result.equals("Sorry,can not find it")) {
            System.out.println("Sorry, this note is not in the notebook.");
        } else {
            ArrayList<Note> notes = noteBook.getNotes();
            for (Note next : notes) {
                if (next.getCourseName().equals(courseName) && next.getTopic().equals(topic)
                        && next.getContent().equals(result)) {
                    noteBook.modifyNoteContents(next, changeTo);
                    System.out.print("Successfully modified!");
                    break;
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a set state behaviour
    private void setTheState() {
        System.out.print("Enter the state of the note (public or private): \n");
        String state = input.next();

        if (state.equals("public") || state.equals("private")) {
            noteBook.setState(state);
        } else {
            System.out.println("Invalid option...");
        }

        System.out.print("Successfully set up!");
    }

    // MODIFIES: this
    // EFFECTS: conducts a search note behaviour
    private void searchNote() {
        String courseName = inputCourseName();
        String topic = inputTopic(courseName, "search");
        String result = noteBook.findNotesByCourseAndTopic(courseName, topic);
        if (result.equals("Sorry,can not find it")) {
            System.out.println(result);
        } else {
            System.out.println(courseName + ": " + topic + "\n" + result);
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts print all notes related to the course behaviour
    private void printCourseNotes() {
        System.out.print("Enter which course notes you are going to print: \n");
        String courseName = input.next();
        ArrayList<Note> notes = noteBook.getCourseNotes(courseName);
        if (notes.isEmpty()) {
            System.out.println("Sorry, you don't have any notes for this course yet. Trying to add one!");
        } else {
            System.out.println(courseName);
            for (Note next : notes) {
                System.out.println(next.getTopic() + ":\n" + next.getContent());
            }
        }
    }

    // EFFECTS: collect the course name input by user
    private static String inputCourseName() {
        System.out.println("Please enter the course name:");
        return input.next();
    }

    // EFFECTS: collect the topic input by user
    private static String inputTopic(String courseName, String do1) {
        System.out.println("What topic of " + courseName + " would you like to " + do1 + " :");
        input.nextLine();
        String topic = input.nextLine();
        return topic;
    }

    // EFFECTS: collect the content input by user
    private static String inputContent(String courseName, String topic) {
        System.out.println("Please enter the note content of " + topic + " of " + courseName + ": ");
        return input.nextLine();

    }

    // EFFECTS: saves the NoteBook to file
    public void saveNoteBook() {
        try {
            jsonWriter.open();
            jsonWriter.write(noteBook);
            jsonWriter.close();
            //System.out.println("Saved " + noteBook.getStudentName() + "'s notes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            //System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads NoteBook from file
    public void loadNoteBook() {
        try {
            noteBook = jsonReader.read();
            //System.out.println("Loaded " + noteBook.getStudentName() + "'s notes from " + JSON_STORE);
        } catch (IOException e) {
            //System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // getters
    public NoteBook getNoteBook() {
        return noteBook;
    }
}
