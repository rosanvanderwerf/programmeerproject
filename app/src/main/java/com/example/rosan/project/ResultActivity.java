package com.example.rosan.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get query from intent
        Intent intent = getIntent();
        query = intent.getStringExtra("query");

        // Use this query to do a request

        Button button = findViewById(R.id.to_network);
        button.setOnClickListener(this);

        TextView title = findViewById(R.id.title);
        title.setText(query);
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, NetworkActivity.class);
        intent.putExtra("query",query);
        startActivity(intent);
    }
}
