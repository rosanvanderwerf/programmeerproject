package com.example.rosan.project;

import android.content.Context;
import android.util.Log;
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

public class TranslationsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    public Context context;
    public Callback cb;



    public interface Callback {
        void gotTranslations(ArrayList<String> translations);
        void gotError(String message);
    }

    /* Constructor */
    TranslationsRequest(Context c) { context = c; }

    public void getTranslations(Callback activity, String language, String query){
        RequestQueue queue = Volley.newRequestQueue((Context) activity);

        //String url = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20180616T143920Z.af7a61726b7432d4.d23e9e4fbe5a0358412bae225e4d853ecf4dd806&lang=" + language + "-en&text=" + query;

        String url = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20180616T143920Z.af7a61726b7432d4.d23e9e4fbe5a0358412bae225e4d853ecf4dd806&lang="+language+"-en&text="+query;
        JsonObjectRequest request_translations = new JsonObjectRequest(url, null, this, this);
        queue.add(request_translations);

        /* Call activity */
        cb = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        cb.gotError("failed to extract translations");
    }

    @Override
    public void onResponse(JSONObject response) {

        ArrayList lst_translations = new ArrayList();

        try{
            JSONArray tr_res = response.getJSONArray("def");
            for (int i=0;i<tr_res.length();i++){

                // get all different query with different pos
                JSONObject tr_ar = tr_res.getJSONObject(i);

                // Extract text and pos
                String text = tr_ar.getString("text");
                String pos = tr_ar.getString("pos");

                // tr (array)
                JSONArray translations = tr_ar.getJSONArray("tr");

                for(int j=0;j<translations.length();j++){
                    // extract object
                    JSONObject tr_ob = translations.getJSONObject(j);

                    // text, pos, syn
                    String tr_t = tr_ob.getString("text");
                    String tr_p = tr_ob.getString("pos");

                    try{
                        JSONArray synonyms = tr_ob.getJSONArray("syn");

                        for(int x=0;x<synonyms.length();x++) {
                            JSONObject syn_ob = synonyms.getJSONObject(x);

                            // text pos
                            String syn_t = syn_ob.getString("text");
                            String syn_p = syn_ob.getString("pos");

                            //Toast.makeText(context, "EN_SYN"+syn_t+syn_p, Toast.LENGTH_SHORT).show();
                            lst_translations.add(syn_t);
                        }
                    } catch (JSONException e){
                        //e.printStackTrace();
                    }
                    //Toast.makeText(context, "EN_TR"+tr_t+tr_p, Toast.LENGTH_SHORT).show();
                    lst_translations.add(tr_t);
                }
                //Toast.makeText(context, "NL"+ text + pos, Toast.LENGTH_LONG).show();

                // check if lst_translations !isEmpty()
                if(!lst_translations.isEmpty()){
                    cb.gotTranslations(lst_translations);
                } else {

                    cb.gotError("failed to extract translations");
                    Toast.makeText(context, "failed translations", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
