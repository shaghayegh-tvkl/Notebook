package com.tavakoli.Label;

import com.tavakoli.Notes.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Label{

    HashSet<String> allLabels;
    String[] noteLabelsArray;

    public Label(ArrayList<Note> allNotes) {
        allLabels = new HashSet<String>();
        setAllLabel(allNotes);
    }


    public void setLabel(String label) {
        allLabels.add(label);
    }

    public void setLabel(String[] label) {
        for(int i = 0; i < label.length; i++)
            allLabels.add(label[i]);
    }

    public void setAllLabel(ArrayList<Note> allNotes) {
        for(int i = 0; i < allNotes.size(); i++){
            Note note = allNotes.get(i);
            noteLabelsArray = note.getNoteLabels().split(",");
            setLabel(noteLabelsArray);
        }
    }

    public String[] getLabel(){
        Object[] labelArray = allLabels.toArray();
        String[] stringArray = Arrays.copyOf(labelArray, labelArray.length, String[].class);
        return stringArray;
    }

    public void getAllLabel(){
        Object[] labelArray = allLabels.toArray();
        for(int i = 0; i < labelArray.length; i++)
            System.out.println(labelArray[i].toString());
    }

}