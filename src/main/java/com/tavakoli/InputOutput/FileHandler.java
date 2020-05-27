package com.tavakoli.InputOutput;

import com.tavakoli.Notes.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public ArrayList<Note> readNotesFromFile() {

        ArrayList<Note> myNotebook = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/java/com/tavakoli/notes.txt");
            Scanner in = new Scanner(fileReader);

            while (in.hasNextLine()) {
                Note note = new Note();
                note.setTitle(in.nextLine());
                note.setDate(in.nextLine());
                note.setText(in.nextLine());
                note.setNoteLabels(in.nextLine());
                myNotebook.add(note);
            }
            in.close();
            return myNotebook;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found !");
            return null;
        }
    }

    public void writeInFile(ArrayList<Note> myNotes) {
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/java/com/tavakoli/notes.txt");
            try {
                for (int i = 0; i < myNotes.size(); i++) {

                    fileOut.write(myNotes.get(i).getTitle().getBytes());
                    fileOut.write("\n".getBytes());
                    fileOut.write(myNotes.get(i).getDate().getBytes());
                    fileOut.write("\n".getBytes());
                    fileOut.write(myNotes.get(i).getText().getBytes());
                    fileOut.write("\n".getBytes());
                    fileOut.write(myNotes.get(i).getNoteLabels().getBytes());
                    fileOut.write("\n".getBytes());
                }
            } catch (Exception e) {
                System.out.println("File Overwrite Error! ");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        }
    }

}