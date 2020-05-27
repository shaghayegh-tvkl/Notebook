package com.tavakoli.Notes;

import java.util.ArrayList;

public class NoteList {

    public void sortByDate(ArrayList<Note> notes){

        for (int i = notes.size() - 1; i > 0 ; i--){
            for(int j = 0; j < i; j++){
                String date[] = notes.get(j).getDate().split(" ");
                String dateNext[] = notes.get(j+1).getDate().split(" ");

                if (Integer.parseInt(date[2]) > Integer.parseInt(dateNext[2])) {
                    Note temp = notes.get(j+1);
                    notes.set(j+1, notes.get(j));
                    notes.set(j, temp);
                }
                else if (Integer.parseInt(date[2]) == Integer.parseInt(dateNext[2])){
                    if (monthOrder(date[1]) > monthOrder(dateNext[1])) {
                        Note temp = notes.get(j+1);
                        notes.set(j+1, notes.get(j));
                        notes.set(j, temp);
                    }
                    else if (monthOrder(date[1]) == monthOrder(dateNext[1])) {
                        if (Integer.parseInt(date[0]) > Integer.parseInt(dateNext[0])) {
                            Note temp = notes.get(j+1);
                            notes.set(j+1, notes.get(j));
                            notes.set(j, temp);
                        }
                    }
                }
            }
        }
    }

    public void sortByTitle(ArrayList<Note> notes){

        for (int i = notes.size() - 1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (notes.get(j).getTitle().compareTo(notes.get(j+1).getTitle()) > 0 ) {
                    Note temp = notes.get(j + 1);
                    notes.set(j + 1, notes.get(j));
                    notes.set(j, temp);
                }
            }
        }

    }

    private int monthOrder(String month){
        switch (month){
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                return -1;
        }
     }
}

