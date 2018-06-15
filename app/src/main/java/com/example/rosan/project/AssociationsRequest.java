package com.example.rosan.project;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class AssociationsRequest implements Response.Listener<JSONObject> , Response.ErrorListener{

    public interface Callback {
        void gotAssociations(ArrayList<String> associations);
        void gotError(String message);
    }

    public Context context;
    public Callback cb;

    /* Constructor */
    AssociationsRequest(Context c){
        context = c;
    }

    public void getAssociations(Callback activity, String query) {
        /* Retrieve associations from API, if zo: notify activity */
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        //String url = context.getString(R.string.as_url_p1)+ query +context.getString(R.string.as_ulr_p2);
        String url = "https://api.wordassociations.net/associations/v1.0/json/search?apikey=b5787245-7478-4ff6-95ca-bb421177f353&text="+ query+"&lang=en";
        JsonObjectRequest request = new JsonObjectRequest(url, null, this, this);
        queue.add(request);

        /* Notify activity when retrieval successful*/
        cb = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        cb.gotError("failed to extract associations");
    }

    @Override
    public void onResponse(JSONObject response) {
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
                cb.gotError("failed to extract associations");
            } else {
                cb.gotAssociations(associations);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
