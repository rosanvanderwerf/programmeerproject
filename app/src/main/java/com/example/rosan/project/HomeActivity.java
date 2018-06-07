package com.example.rosan.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
                // start new intent
                Toast.makeText(this, "enter is pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ResultActivity.class);
                EditText input = findViewById(R.id.query);
                String query = input.getText().toString();
                intent.putExtra("query",query);
                startActivity(intent);
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

}
