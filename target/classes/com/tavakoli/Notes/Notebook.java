package com.tavakoli.Notes;

import com.tavakoli.InputOutput.FileHandler;
import com.tavakoli.Label.Label;
import com.tavakoli.Label.Titles;

import java.util.ArrayList;
import java.util.Scanner;


public class Notebook {
    ArrayList<Note> myNotes;
    ArrayList<Note> archiveNotes;

    Label allLabels;
    FileHandler file = new FileHandler();
    Titles allTitles;

    Scanner inChoice = new Scanner(System.in);
    Scanner inSubChoice = new Scanner(System.in);
    Scanner inData = new Scanner(System.in);


    int choice, subChoice;

    public Notebook(ArrayList<Note> notes){
        myNotes = notes;
        allLabels = new Label(myNotes);
        allTitles = new Titles(allLabels, myNotes);
        allTitles.setLabel();
        archiveNotes = new ArrayList<>();
    }

    public void showMenu(){
        do {
            System.out.println("Press 1 To View Notes" +
                    "\nPress 2 To Add a New Note" +
                    "\nPress 3 To Pin/Unpin a Note" +
                    "\nPress 4 To Search For Notes" +
                    "\nPress 5 To Archive/Unarchive Notes" +
                    "\nPress 6 To Delete a Note" +
                    "\nPress 0 To Close The Notebook");
            choice = inChoice.nextInt();
            switch(choice){
                case 1:
                    System.out.println("\tPress 1 To View All Notes Sorted By Date" +
                            "\n\tPress 2 To View All Notes Sorted by Titles" );
                    subChoice = inSubChoice.nextInt();
                    NoteList myNoteList = new NoteList();
                    switch(subChoice){
                        case 1:
                            myNoteList.sortByDate(myNotes);
                            showAllNotes();

                            break;
                        case 2:
                            myNoteList.sortByTitle(myNotes);
                            showAllNotes();
                            break;
                    }
                    break;

                case 2:
                    addNote();
                    break;

                case 3:
                    System.out.println("Enter Note Title To Pin/Unpin: \n");
                    showAllTitles(myNotes);
                    String noteTitle = inData.nextLine();
                    pinUnpinNote(noteTitle);
                    break;

                case 4:
                    System.out.println("\tPress 1 To Search By Title" +
                            "\n\tPress 2 To Search By Label" +
                            "\n\tPress 3 To Search A Word In All Notes");
                    subChoice = inSubChoice.nextInt();
                    switch (subChoice){
                        case 1:
                            System.out.println("Enter Title: ");
                            noteTitle = inData.nextLine();
                            searchTitle(noteTitle);
                            break;

                        case 2:
                            allLabels.getAllLabel();
                            System.out.println("Enter Label \"\"Case Sensitive\"\" : ");
                            String noteLabel = inData.nextLine();
                            searchLabel(noteLabel);
                            break;

                        case 3:
                            System.out.println("Enter Word \"\"Case Sensitive\"\" : ");
                            String word = inData.nextLine();
                            searchWord(word);
                            break;
                    }
                    break;


                case 5:
                    System.out.println("Enter Note Title To Archive: \n");

                    showAllTitles(myNotes);
                    System.out.println("-Archived-");
                    showAllTitles(archiveNotes);

                    noteTitle = inData.nextLine();
                    archiveNote(noteTitle);
                    break;

                case 6:
                    System.out.println("Enter Note Title To Delete: \n");
                    showAllTitles(myNotes);
                    noteTitle = inData.nextLine();
                    deleteNote(noteTitle);
                    break;

            }
        }while(choice != 0);
    }

    private void showAllNotes(){
        for(int i = 0; i < myNotes.size(); i++){
            Note note = myNotes.get(i);
            if(note.isPinned()){
                System.out.println("\nPinned Note: " +
                        "\nTitle: " + note.getTitle() +
                        "\t\tDate: " + note.getDate() +
                        "\n\t " + note.getText() +
                        "\n Labels: "  + note.getNoteLabels());
            }
        }
        System.out.println("----------------------------");
        for(int i = 0; i < myNotes.size(); i++){
            Note note = myNotes.get(i);
            if (!(note.isPinned())) {
                System.out.println("\nTitle: " + note.getTitle() +
                        "\t\tDate: " + note.getDate() +
                        "\n\t " + note.getText() +
                        "\n Labels: " + note.getNoteLabels());
            }
        }
        System.out.println("\n");
      }
    private void showAllTitles(ArrayList<Note> myNotes){
        for(int i = 0; i < myNotes.size(); i++){
            Note note = myNotes.get(i);
            if(note.isPinned()){
                System.out.println("Pinned Note: " +
                        "\nTitle: " + note.getTitle());
            }
        }
        System.out.println("----------------------------");

        for(int i = 0; i < myNotes.size(); i++){
            Note note = myNotes.get(i);
            if (!(note.isPinned())) {
                System.out.println("Title: " + note.getTitle());
            }
        }

        System.out.println("\n");
    }

    private void addNote(){
        String data;
        Note newNote = new Note();
        System.out.println("Title: ");
        data = inData.nextLine();
        newNote.setTitle(data);
        do{
            System.out.println("Date ( e.g.: 17 Jan 2017 ): ");
            data = inData.nextLine();
        }while (!(isDateValid(data)));
        newNote.setDate(data);
        System.out.println("Note: ");
        data = inData.nextLine();
        newNote.setText(data);

        for(int i = 0; i < 10; i++) {
            System.out.println("Labels:\n -Available Labels- ");
            allLabels.getAllLabel();
            System.out.println(" \"Add New Label\" " +
                    "\n\tEnter 'Done' When You Add All Your Labels (Not More Than 10 Labels Allowed) ");
            data = inData.nextLine();
            if (!(data.equalsIgnoreCase("done"))) {

                if (isLabelValid(data)) {
                    newNote.setLabels(data.toLowerCase());
                    System.out.println("Label Added. ");
                } else {
                    allLabels.setLabel(data);
                    newNote.setLabels(data);
                }
            }
            else
                break;
        }
        removeLabelDuplicate(newNote);
        myNotes.add(newNote);
        file.writeInFile(myNotes);
    }

    private boolean isDateValid(String dateInput){
        String[] date = new String[3];
        date = dateInput.split(" ");
        if(Integer.parseInt(date[0]) > 0 && Integer.parseInt(date[0]) < 32){
            if(isMonthValid(date[1])){
                if(date[2].length() == 4)
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        else
            return false;
    }
    private boolean isMonthValid(String month){
        if (month.equals("Jan")
                || month.equals("Feb")
                || month.equals("Mar")
                || month.equals("Apr")
                || month.equals("May")
                || month.equals("Jun")
                || month.equals("Jul")
                || month.equals("Aug")
                || month.equals("Sep")
                || month.equals("Oct")
                || month.equals("Nov")
                || month.equals("Dec") ){
            return true;
        }
        else
            return false;
    }

    private boolean isLabelValid(String label){
        String[] validLabels = allLabels.getLabel();
        for (int i = 0; i < validLabels.length; i++){
            if (validLabels[i].equalsIgnoreCase(label)){
                return true;
            }
        }
        return false;
    }
    private void removeLabelDuplicate(Note labels){
        String[] noteLabels = labels.getNoteLabels().split(",");
        String[] check = noteLabels.clone();
        for(int i = 0; i < noteLabels.length; i++){
            for(int j = 0; j < noteLabels.length; j++){
                if (check[i].equalsIgnoreCase(noteLabels[j]));
                noteLabels[j] = "    ";
            }
        }
    }

    private void pinUnpinNote(String noteTitle){
        for(int i = 0; i < myNotes.size(); i++){
            if(myNotes.get(i).getTitle().equalsIgnoreCase(noteTitle)){
                if(myNotes.get(i).isPinned())
                    myNotes.get(i).setPin(false);
                else
                    myNotes.get(i).setPin(true);
                System.out.println("Done. ");
            }
        }
    }


    private void searchTitle(String noteTitle){
        for(int i = 0; i < myNotes.size(); i++){
            if(myNotes.get(i).getTitle().equalsIgnoreCase(noteTitle)){
                Note note = myNotes.get(i);
                System.out.println("Title: " + note.getTitle() +
                        "\t\tDate: " + note.getDate() +
                        "\n\t " + note.getText() +
                        "\n Labels: " + note.getNoteLabels());
            }
        }
        System.out.println("\n");
    }
    private void searchLabel(String noteLabel){
        ArrayList<String> resultTitles = allTitles.labelTitle.get(noteLabel);
        System.out.println("This Notes Include #" + noteLabel + ": ");
        for(int i = 0; i < resultTitles.size(); i++) {
            searchTitle(resultTitles.get(i));
        }
    }
    private void searchWord(String word){
        String text;
        for(int i = 0; i < myNotes.size(); i++){
            Note note = myNotes.get(i);
            if(myNotes.get(i).getText().toLowerCase().contains(word.toLowerCase())){
                text = note.getText().replaceAll(word,"<<"+word+">>");
                if(note.isPinned()){
                    System.out.println("\nPinned Note: " +
                            "\nTitle: " + note.getTitle() +
                            "\t\tDate: " + note.getDate() +
                            "\n\t " + text +
                            "\n Labels: " + note.getNoteLabels());
                }
            }
        }

        System.out.println("----------------------------");

        for(int i = 0; i < myNotes.size(); i++){
            Note note = myNotes.get(i);
            if(myNotes.get(i).getText().contains(word)){
                text = note.getText().replaceAll(word,"<<"+word+">>");
                if(!(note.isPinned())){
                    System.out.println("\nTitle: " + note.getTitle() +
                            "\t\tDate: " + note.getDate() +
                            "\n\t " + text +
                            "\n Labels: " + note.getNoteLabels());
                }
            }
        }
        System.out.println("\n");
    }

    private void deleteNote(String noteTitle){
        for(int i = 0; i < myNotes.size(); i++){
            if(myNotes.get(i).getTitle().equalsIgnoreCase(noteTitle)){
                myNotes.remove(i);
                System.out.println("Note Deleted. ");
            }
        }

        file.writeInFile(myNotes);
    }
    private void archiveNote(String noteTitle){
        boolean archived = false;
        Note archivedNote = null;
        for(int i = 0; i < myNotes.size(); i++){
            if(myNotes.get(i).getTitle().equalsIgnoreCase(noteTitle)){
                archiveNotes.add(myNotes.get(i));
                myNotes.remove(i);
                System.out.println("Note Archived. ");
                archived = true;
            }
        }

        for(int i = 0; i < archiveNotes.size(); i++){
            if(!archived){
                if(archiveNotes.get(i).getTitle().equalsIgnoreCase(noteTitle)){
                    myNotes.add(archiveNotes.get(i));
                    System.out.println("Note Unarchived. ");
                    archiveNotes.remove(i);
                    archived = true;
                }
            }
        }

        file.writeInFile(myNotes);
    }


}
