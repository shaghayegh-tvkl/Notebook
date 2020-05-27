package com.tavakoli.Label;

import com.tavakoli.Notes.Note;
import java.util.ArrayList;
import java.util.HashMap;

public class Titles {
    public HashMap<String,ArrayList<String>> labelTitle;
    public String[] labels;
    public ArrayList<Note> allNotes;

    public Titles(Label allLabels, ArrayList<Note> allNotes){
        labelTitle = new HashMap<>();
        labels = allLabels.getLabel();
        this.allNotes = allNotes;
    }

    public void setLabel(){
        for(int i = 0; i < labels.length; i++) {
            ArrayList<String> titles = new ArrayList<>();
            for (int j = 0; j < allNotes.size(); j++) {
                if (allNotes.get(j).getNoteLabels().contains(labels[i])) {
                    titles.add(allNotes.get(j).getTitle());
                }
            }
            labelTitle.put(labels[i], titles);
        }
    }
}



