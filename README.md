# My Personal Project: NoteBook

## NoteBook Application
The application aims to serve learners a good learning environment during study and work. 
Different from a traditional notebook, an electronic version will be more convenient to find, 
add and delete notes, which greatly saves time and improves learning efficiency. 
In my notebook project, users can print review sheets, and view historical notes 
at any time before the exam. These notes can also be divided into different 
categories such as courses, chapters, and levels of importance to reduce 
stress for exam review.

This application is designed for students to record their study processes. 
As a university student I deeply feel the benefits of electronic notes, which will be more **portable**, and **flexible**. 
You can review notes anytime by accessing your notes with electronic devices. 
Also, it is easy to share with your friends.

## User Stories
A *User Stories* list:
- As a user, I want to be able to add a note to my NoteBook
- As a user, I want to be able to delete a note from my NoteBook
- As a user, I want to be able to modify a note from my NoteBook
- As a user, I want to be able to search the note that I need
- As a user, I want to be able to get the summary notes of one course
- As a user, I want to be able to set my notes to public or private
- As a user, I want to be able to save my NoteBook to file 
- As a user, I want to be able to be able to load my NoteBook from file 

## Phase 4: Task 2: Log Events
When the user quits the NoteBook application, the application will print to the console all the events 
that have been logged since the application started.

**The events supported through GUI in phase 3**

- Load NoteBook to the system
- Add a note to the NoteBook
- Delete a note from the NoteBook
- Modify a note from the NoteBook
- Search the note from the NoteBook
- Save NoteBook to the system


**The representative sample of the events are the following:**

Wed Mar 30 00:31:50 PDT 2022
Read notebook from a file

Wed Mar 30 00:31:50 PDT 2022
Course: cpsc-210; Topic: interface, was added as new note.

Wed Mar 30 00:31:50 PDT 2022
Course: cpsc-121; Topic: proof, was added as new note.

Wed Mar 30 00:32:30 PDT 2022
Course: cpsc-110; Topic: wrong , was added as new note.

Wed Mar 30 00:32:42 PDT 2022
Searched a note of cpsc-110.

Wed Mar 30 00:32:58 PDT 2022
Searched a note of cpsc-110.

Wed Mar 30 00:33:08 PDT 2022
Content: test!! has changed to te.

Wed Mar 30 00:33:19 PDT 2022
Content: te has changed to test works!!.

Wed Mar 30 00:33:22 PDT 2022
Course: cpsc-110; Topic: wrong , has removed.

Wed Mar 30 00:33:34 PDT 2022
Saved notebook to a file

## Phase 4: Task 3 Reflect on the design

If I had more time to work on the project, I will improve my design in the following points:
1. Apply Observer design pattern in the NoteBookGUI class. 
Because when a user add, modify, delete, and load notes, the overview notebook table will change accordingly.
And this can be modeled by the Observer design pattern to increase the cohesion and reduce duplicate code.


2. Separate the NoteBookGUI frame into top panel and table panel class to enhance Single Responsibility Principle.


3. Apply Iterator design pattern in the NoteBook class, so that it can provide a way to access the single note of 
a notebook sequentially without exposing its underlying implementation.


 