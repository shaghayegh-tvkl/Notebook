package com.tavakoli;

import com.tavakoli.InputOutput.FileHandler;
import com.tavakoli.Notes.Note;
import com.tavakoli.Notes.Notebook;
import java.util.ArrayList;
public class App {

    public static void main(String[] args) {

        FileHandler file = new FileHandler();
        ArrayList<Note> previousNotes = file.readNotesFromFile();
        Notebook myNotebook = new Notebook(previousNotes);
        myNotebook.showMenu();

    }
}
