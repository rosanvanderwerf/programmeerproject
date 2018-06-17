package com.example.rosan.project;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

public class WordRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    public interface Callback {
        void gotWordDetails(Word word);
        void gotWordError(String message);
    }

    public Context context;
    public Callback cb;

    /* Constructor */
    WordRequest(Context c){
        context = c;
    }

    public void getWord(Callback activity, String query){

        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        String url = "https://api.wordnik.com/v4/word.json/"+query+"/definitions?limit=200&includeRelated=false&sourceDictionaries=all&useCanonical=false&includeTags=false&api_key=15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";
        JsonObjectRequest request_definitions = new JsonObjectRequest(url, null, this, this);
        queue.add(request_definitions);

        /* Notify activity */
        cb = activity;
    }

    @Override
    public void onResponse(JSONObject response) {

        Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(context, "no", Toast.LENGTH_LONG).show();
        cb.gotWordError("failed to extract details");
    }
}
