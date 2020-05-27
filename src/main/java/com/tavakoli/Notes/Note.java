package com.tavakoli.Notes;

public class Note {
    private String title;
    private String date;
    private String text;
    private String noteLabels;
    private boolean pin;

    public Note() {
        pin = false;
        noteLabels = " ";
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String myTitle) {
        title = myTitle;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String myDate) {
        date = myDate;
    }

    public String getText() {
        return text;
    }
    public void setText(String myText) {
        text = myText;
    }

    public boolean isPinned(){
        return pin;
    }
    public void setPin(boolean condition){
        pin = condition;
    }

    public String getNoteLabels() {
        return noteLabels.trim();
    }

    public void setNoteLabels(String myNoteLabels) {
        noteLabels = myNoteLabels;
    }

    public void setLabels(String myNoteLabels) {
        noteLabels += myNoteLabels + ",";
    }

}