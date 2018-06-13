package com.example.rosan.project;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;
import java.util.ArrayList;
public class WordRequest implements Response.Listener<JSONObject>, Response.ErrorListener{



    public interface Callback {
        void gotWordDetails(ArrayList<String> associations);
        void gotError(String message);
    }

    public Context context;
    public AssociationsRequest.Callback cb;

    /* Constructor */
    WordRequest(Context c){
        context = c;
    }


    public void getWord(AssociationsRequest.Callback activity, String query) throws UnirestException {
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        String word_key = "15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";

        /* Get word definitions */
        /*String url = context.getString(R.string.de_url_p1) + query + context.getString(R.string.de_url_p2) + word_key;
        JsonObjectRequest request_definitions = new JsonObjectRequest(url, null, this, this);
        queue.add(request_definitions);*/

        /* Get word examples */
        /* project: default-application_5b100586e4b091d6b3344045*/
        /* API: 36b10b0d-0517-4169-89e1-3286c71126dd*/
        /* EIF0TecghjmshkUPcq7T0oHTnQKep1fhgbljsnqVhSuEjK8Kn7*/

        /*try {
            Unirest.get("https://wordsapiv1.p.mashape.com/words/soliloquy")
                    .header("X-Mashape-Key", "EIF0TecghjmshkUPcq7T0oHTnQKep1fhgbljsnqVhSuEjK8Kn7")
                    .asJson();
        } catch (UnirestException e){
            e.printStackTrace();
            // seomthing wrong
        }*/
//        Unirest.get("https://wordsapiv1.p.mashape.com/words/soliloquy")
//                .header("X-Mashape-Key", word_key)
//                .header("Accept","application/json")
//                .asJson();
    HttpResponse<JsonNode> jsonResponse = Unirest.post("https://wordsapiv1.p.mashape.com/")
            .header("words/", word_key)
            .asJson();






    }

    @Override
    public void onResponse(JSONObject response) {

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }




}
