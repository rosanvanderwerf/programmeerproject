package com.example.rosan.project;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class WordObjectRequest implements Response.Listener<JSONArray>, Response.ErrorListener{



    public interface Callback {
        void gotWordDetails(Word word);
        void gotWordError(String message);
    }

    public Context context;
    public Callback cb;
    String str_query;

    String str_partOfSpeech;
    String str_text;
    String audio_url;
    String str_total_def;


    Word word;


    /* Constructor */
    WordObjectRequest(Context c){
        context = c;
    }

    public void getWord(Callback activity, String url, String query){

        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        //String url = "https://api.wordnik.com/v4/word.json/"+query+"/definitions?limit=200&includeRelated=false&sourceDictionaries=all&useCanonical=false&includeTags=false&api_key=15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";
        JsonArrayRequest request_definitions = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(request_definitions);

        // Make query a global variable in this Class
        str_query = query;

        /* Notify activity */
        cb = activity;
    }

    @Override
    public void onResponse(JSONArray response) {
        //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();

        // Create new Word instance
        // word = new Word(parameters)

        // EXTRACT: definitions
        // voor elk element in de Array, itereer door elk object

        ArrayList<String> definitions = new ArrayList<>();

        try {
            for(int i =0;i<response.length();i++){
                JSONObject first = response.getJSONObject(i);
                // get partOfSpeech and definition
                str_partOfSpeech = first.getString("partOfSpeech");
                str_text = first.getString("text");
                str_total_def = str_partOfSpeech + " " + str_text;
                definitions.add(str_total_def);
            }
        } catch (JSONException e) {
            //e.printStackTrace();
        }

        // EXTRACT: audio_url
        try {
            JSONObject one = response.getJSONObject(0);
            audio_url = one.getString("fileUrl");
        } catch (JSONException e) {
            //e.printStackTrace();
        }

        // EXTRACT: hyphenations

        // Make Word instance and give to Resultactivity
        Word word = new Word(str_query, audio_url,definitions, 8);
        cb.gotWordDetails(word);



    }

    @Override
    public void onErrorResponse(VolleyError error) {

        cb.gotWordError("wordrequest: onErrorResponse activated");//error.toString());
    }
}
