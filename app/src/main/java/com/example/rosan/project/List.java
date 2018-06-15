package com.example.rosan.project;

import java.util.ArrayList;

public class List {

    public int _id;
    public String listname;
    public String timestamp;
    ArrayList words;


    // Constructor: save a new list with just a listname
    public List(String listname){//, ArrayList<String> words){
        this.listname = listname;
    }

    // Constructor for adding timestamp and content (words)
    public List(Integer id, String listname, String timestamp, ArrayList words){
        this._id = id;
        this.listname = listname;
        this.timestamp = timestamp;
        this.words = words;
    }

    // Getters
    public String getListname(){
        return listname;
    }

    public String getTimestamp(){
        return timestamp;
    }

    /*public Long getWords() {
        return words;
    }*/

    // Setters
    public void setListname(String newListname){
        this.listname = newListname;
    }

    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }

    public void setWords(ArrayList newWords){
        this.words = newWords;
    }



}
