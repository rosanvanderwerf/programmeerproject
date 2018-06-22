package com.example.rosan.project;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class WordArrayRequest implements Response.Listener<JSONObject> , Response.ErrorListener{

    public interface Callback {
        void gotAssociations(ArrayList<String> associations);
        void gotAssociationsError(String message);
    }

    public Context context;
    public Callback cb;

    /* Constructor */
    WordArrayRequest(Context c){
        context = c;
    }

    public void getDetails(Callback activity, String url, String query) {

        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        //String url = "https://api.wordassociations.net/associations/v1.0/json/search?apikey=b5787245-7478-4ff6-95ca-bb421177f353&text="+ query+"&lang=en";
        JsonObjectRequest request_associations = new JsonObjectRequest(url, null, this, this);
        queue.add(request_associations);

        /* Notify activity when retrieval successful*/
        cb = activity;
    }

    @Override
    public void onResponse(JSONObject response) {

        // EXTRACT: associations
        try {
            JSONArray as_res = response.getJSONArray("response");
            JSONObject itemsObject = as_res.getJSONObject(0);
            JSONArray itemsArray = itemsObject.getJSONArray("items");

            // for item in itemsArray: add "item" string to StringArray
            ArrayList<String> associations = new ArrayList<>();

            for (int i=0;i<itemsArray.length();i++){
                JSONObject as = itemsArray.getJSONObject(i);
                String item = as.getString("item");
                associations.add(item);
            }

            if(associations.isEmpty()){
                cb.gotAssociationsError("failed to extract associations");
            } else {
                cb.gotAssociations(associations);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // EXTRACT: examples
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        cb.gotAssociationsError("failed to extract associations");
    }
}
