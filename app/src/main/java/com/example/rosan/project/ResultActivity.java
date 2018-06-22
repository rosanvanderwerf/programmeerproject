package com.example.rosan.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener,  WordObjectRequest.Callback, WordArrayRequest.Callback {

    String query;
    TextView title;
    ImageView play_audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get query from intent
        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        // Use this query to do a request
        //RapidApiConnect connect = new RapidApiConnect("default-application_5b100586e4b091d6b3344045",
                //"36b10b0d-0517-4169-89e1-3286c71126dd");

        // Word OBJECT requests: definitions, audio, and the like
        WordObjectRequest de_request = new WordObjectRequest(this);
        WordObjectRequest audio_request = new WordObjectRequest(this);
        WordObjectRequest examples_request = new WordObjectRequest(this);

        // Word ARRAY requests: associations, hyphenations
        WordArrayRequest as_request = new WordArrayRequest(this);
        WordArrayRequest hyph_request = new WordArrayRequest(this);

        String word_api_key = "15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";
        String associations_api_key = "b5787245-7478-4ff6-95ca-bb421177f353";

            // WORDNIK: request ARRAY: definitions, audio
        String de_url = "https://api.wordnik.com/v4/word.json/"+query+"/definitions?limit=200&includeRelated=false&sourceDictionaries=all&useCanonical=false&includeTags=false&api_key=" + word_api_key;
        String audio_url = "https://api.wordnik.com/v4/word.json/"+query+"/audio?useCanonical=false&limit=50&api_key="+word_api_key;
        String examples_url = "https://api.wordnik.com/v4/word.json/"+query+"/examples?includeDuplicates=false&useCanonical=false&limit=5&api_key="+word_api_key;

        // ASSOCIATIONS: request assocations
        String associations_url = "https://api.wordassociations.net/associations/v1.0/json/search?apikey="+associations_api_key+"&text="+ query+"&lang=en";
        String hyphenations_url = "https://api.wordnik.com/v4/word.json/whenever/hyphenation?useCanonical=false&limit=50&api_key=15f5aecd00aed7acd70060d1fe20b9eab2f7787e3128cb25a";//"https://api.wordnik.com/v4/word.json/"+query+"/hyphenation?useCanonical=false&limit=50&api_key="+word_api_key;

        // WORDNIK: object requests: word, audio_url, examples, hyphenations
        de_request.getWord(this, de_url, query);
        audio_request.getWord(this, audio_url, query);
        examples_request.getWord(this, examples_url, query);

        // WORDNIK & ASSOCIATIONS: array requests: associations, hyphenations
        as_request.getDetails(this,associations_url,query);
        hyph_request.getDetails(this, hyphenations_url, query);



        // Initiate views
        play_audio = findViewById(R.id.play_audio);
        play_audio.setOnClickListener(new playAudio());

        Button button = findViewById(R.id.to_network);
        button.setOnClickListener(this);

        // Set title to the searched word/ query
        title = findViewById(R.id.title);
        title.setText(query);
    }


    /* Underneath: handling requests */

    @Override
    public void gotAssociations(ArrayList<String> associations) {
        TextView response = findViewById(R.id.response);
        response.setText(associations.toString());
    }

    @Override
    public void gotAssociationsError(String message) {
        Log.d("as_request error",message);
    }


    @Override
    public void gotWordDetails(Word word) {
        // Fill views with content of Word
        title.setText(word.getDefinitions().toString());
        UpdateUI(word);
    }

    private void UpdateUI(Word word) {
        // views for: definitions, frequency


    }

    @Override
    public void gotWordError(String message) {
        //Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }





    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NetworkActivity.class);
        intent.putExtra("query",query);
        startActivity(intent);
    }

    private class playAudio implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // play word
            Toast.makeText(ResultActivity.this, "audio should play jwz", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.saved:

                //Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, OverviewActivity.class);
                startActivity(intent);
                break;
            case R.id.about:

                Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:

                Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
