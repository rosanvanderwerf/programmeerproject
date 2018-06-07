package com.example.rosan.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }

    // Andere menu om mee te kunnen sorteren: alfbetisch, date added en custom (zelfde als longlick, hierboven)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.alphabet:
                // Sort alphabetically
                break;
            case R.id.date:
                // Sort on date
                break;
            case R.id.custom:
                // Change UI
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
