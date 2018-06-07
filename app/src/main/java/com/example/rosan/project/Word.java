package com.example.rosan.project;

public class Word {
    public String word;
    public String[] syllables;
    public String pronunciation;
    public int frequency;
    public String[] associations;
    public String timestamp;

    // Constructor
    public Word(String word, String[] syllables, String pronunciation, int frequency, String[] associations){
        this.word = word;
        this.syllables = syllables;
        this.pronunciation = pronunciation;
        this.frequency = frequency;
        this.associations = associations;
    }

    // Different contructor because timestamp does not have to be given by user
    public Word(String word, String[] syllables, String pronunciation, int frequency, String[] associations, String timestamp){
        this.word = word;
        this.syllables = syllables;
        this.pronunciation = pronunciation;
        this.frequency = frequency;
        this.associations = associations;
        this.timestamp = timestamp;
    }

    // Getters

    // Setters
}
