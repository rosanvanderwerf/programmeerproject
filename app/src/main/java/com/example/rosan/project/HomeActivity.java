package com.example.rosan.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/* TO DO
*  Default language is Dutch, updateUI accordingly
*
*  FireBase connectie?
*  Inloggen
*
* */


public class HomeActivity extends AppCompatActivity implements View.OnClickListener, TranslationsRequest.Callback {

    Button nl;
    Button fr;
    Button de;
    Button it;

    WordObjectRequest de_request;

    private ListView lv_translations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // The default language is dutch

        // Random: https://api.wordnik.com/v4/words.json/randomWord?hasDictionaryDef=true&maxCorpusCount=-1&minDictionaryCount=1&maxDictionaryCount=-1&minLength=5&maxLength=-1&api_key=YOURAPIKEY
        // WotD:   https://api.wordnik.com/v4/words.json/wordOfTheDay?date=2018-06-22&api_key=YOURAPIKEY


        // Initiate Buttons and other views
        nl = findViewById(R.id.btn_nl);
        fr = findViewById(R.id.btn_fr);
        de = findViewById(R.id.btn_de);
        it = findViewById(R.id.btn_it);

        lv_translations = findViewById(R.id.translations);

        // Set ClickListeners on Buttons
        nl.setOnClickListener(this);
        fr.setOnClickListener(this);
        de.setOnClickListener(this);
        it.setOnClickListener(this);

        lv_translations.setOnItemClickListener(new displayWord());

        // Requests for random word/word of the day
    }

    @Override
    public void onClick(View v) {

        // When button is clicked
        switch (v.getId()) {
            case R.id.btn_nl:
                // Function: all button background colors white
                //language_nl = "nl";
                lv_translations.setAdapter(null);
                break;

            case R.id.btn_fr:
                //language_fr = "fr";
                break;

            case R.id.btn_de:
                //language_de = "de";
                break;

            case R.id.btn_it:
                //language_it = "it";
                break;

            default:
                break;
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
                Intent intent_overview = new Intent(this, OverviewActivity.class);
                startActivity(intent_overview);
                break;
            case R.id.about:
                Intent intent_about = new Intent(this, AboutActivity.class);
                startActivity(intent_about);
                break;
            case R.id.settings:
                Intent intent_settings = new Intent(this, SettingsActivity.class);
                startActivity(intent_settings);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                lv_translations.setAdapter(null);

                // Check if searched word is going to give a (usable) response
                EditText input = findViewById(R.id.query);
                String query = input.getText().toString();

                if(!query.isEmpty()){

                    TranslationsRequest tr_request = new TranslationsRequest(this);
                    tr_request.getTranslations(this, "nl", query);
                } else {
                    Toast.makeText(this, "Please input a word", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    @Override
    public void gotTranslations(ArrayList<String> translations) {
        //Toast.makeText(this, translations.toString(), Toast.LENGTH_LONG).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, translations);

        lv_translations.setAdapter(adapter);
    }

    @Override
    public void gotError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private class displayWord implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Extract selected word as String
            String selectedQuery = (lv_translations.getItemAtPosition(position)).toString();

            // Go to ResultActivity; with selected query as extra
            Intent intent = new Intent(HomeActivity.this, ResultActivity.class);
            intent.putExtra("query",selectedQuery);

            // empty ListView
            lv_translations.setAdapter(null);

            startActivity(intent);
        }
    }
}
