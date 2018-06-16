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

    public Context context;
    public AssociationsRequest.Callback cb;

    public interface Callback {
        void gotWordDetails(ArrayList<String> associations);
        void gotError(String message);
    }

    /* Constructor */
    WordRequest(Context c){
        context = c;
    }


    public void getWord(AssociationsRequest.Callback activity, String query){
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        //String word_key = "15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";
        /* Get word partOfSpeech, text */
        String url = "https://api.wordnik.com/v4/word.json/house/definitions?limit=200&partOfSpeech=noun&includeRelated=false&sourceDictionaries=all&useCanonical=false&includeTags=false&api_key=15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";//"https://api.wordnik.com/v4/word.json/" + query + "/definitions?limit=200&partOfSpeech=noun&includeRelated=false&sourceDictionaries=all&useCanonical=false&includeTags=false&api_key=" + word_key;
        JsonObjectRequest request_definitions = new JsonObjectRequest(url, null, this, this);
        queue.add(request_definitions);

        /* Get word examples */

        cb = activity;

        /* project: default-application_5b100586e4b091d6b3344045*/
        /* API: 36b10b0d-0517-4169-89e1-3286c71126dd*/
        /* EIF0TecghjmshkUPcq7T0oHTnQKep1fhgbljsnqVhSuEjK8Kn7*/
    }

    @Override
    public void onResponse(JSONObject response) {
        //

        ArrayList<String> associations = new ArrayList<>();

        try {
            JSONArray de_res = response.getJSONArray("response");
            for (int i=0;i<de_res.length();i++){
                JSONObject as = de_res.getJSONObject(i);
                Toast.makeText(context, as.toString(), Toast.LENGTH_SHORT).show();
            }

            // should have got worddetails something

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        cb.gotError("failed to extract details");
    }
}
