package com.example.rosan.project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OverviewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // Make contact with the DB containing all the lists

        // When Floating Action Button is pressed, give popup
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // When longclick item of ListView


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

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(OverviewActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_add_list, null);

        builder.setView(view);
        final AlertDialog dialog = builder.create();

        // Define views
        final EditText list_name = view.findViewById(R.id.list_name);
        Button cancel = view.findViewById(R.id.cancel);
        Button add = view.findViewById(R.id.add);

        // cancel button is clicked
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cancel button is clicked, close dialog
                dialog.dismiss();
            }
        });

        // add button is clicked
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make sure name_list is not empty
                if(!list_name.getText().toString().isEmpty()){
                    Toast.makeText(OverviewActivity.this, "list is added!", Toast.LENGTH_SHORT).show();
                    // Add list as instance of class:Lists

                    // Close dialog
                    dialog.dismiss();
                } else {
                    // name_list is empty, notify user a name must be given
                    Toast.makeText(OverviewActivity.this, "Please enter a list name", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Actually display the dialog
        dialog.show();
    }
}
