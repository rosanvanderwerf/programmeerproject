package com.example.rosan.project;

import java.util.ArrayList;

public class Word {
    public String word;
    public String audio_url;
    public ArrayList<String> definitions;
    public String[] examples;
    public String[] syllables;
    public String pronunciation;
    public int frequency;
    public String[] associations;
    public String timestamp;

    public Word(String word, String audio_url, ArrayList<String> definitions, /*String[] examples,*/ Integer frequency){
        this.word = word;
        this.audio_url=audio_url;
        this.definitions=definitions;
        //this.examples=examples;
        this.frequency=frequency;
    }


    // Constructor
    public Word(String word, String audio_url, String[] syllables, String pronunciation, int frequency){
        this.word = word;
        this.audio_url = audio_url;
        //this.syllables = syllables;
        //this.pronunciation = pronunciation;
        this.frequency = frequency;
    }

    // Different contructor because timestamp does not have to be given by user
    public Word(String word, String audio_url,String[] syllables, String pronunciation, int frequency, String[] associations, String timestamp){
        this.word = word;
        this.audio_url = audio_url;
        this.syllables = syllables;
        this.pronunciation = pronunciation;
        this.frequency = frequency;
        this.associations = associations;
        this.timestamp = timestamp;
    }

    // Getters
    public String getWord(){
        return word;
    }

    public String getAudio_url() { return audio_url; }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public Integer getFrequency() {return frequency; }


    // Setters
}
