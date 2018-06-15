package com.example.rosan.project;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/* TO DO
* When to other activity: save sort-preferences
*
* */

public class OverviewActivity extends AppCompatActivity implements View.OnClickListener {

    ListDatabase db;
    ListView lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        // initiate views
        lists = findViewById(R.id.list);

        // Create database
        db = ListDatabase.getInstance(getApplicationContext());

        // When Floating Action Button is pressed, give popup
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        // Set listeners
        lists.setOnItemClickListener(new displayList());
        lists.setOnItemLongClickListener(new deleteList());


        loadListsList();
    }

    private void loadListsList(){

        // Get data from database and set adapter (auto: on date added)
        ListsAdapter adapter = new ListsAdapter(OverviewActivity.this, db.selectAll());
        lists.setAdapter(adapter);
    }

    private void loadListsAlph(){

        // Sort alphabetically
        ListsAdapter adapter = new ListsAdapter(OverviewActivity.this, db.selectAllAlph());
        lists.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.back:

                //Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show();
                Intent intent_home = new Intent(this, HomeActivity.class);
                startActivity(intent_home);
                break;

            case R.id.alphabet:

                // First empty listview and then set new adapter where listnames are alphabetically
                lists.setAdapter(null);
                loadListsAlph();
                break;

            case R.id.date:

                // Sort on date
                lists.setAdapter(null);
                loadListsList();
                break;

            case R.id.custom:

                // Change UI: remove fab, three lines and select items
                // menu: select all, edit, remove, add new
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
                    List list = new List(list_name.getText().toString());
                    db.insert(list);

                    loadListsList();
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

    private class displayList implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent_list = new Intent(OverviewActivity.this, ListActivity.class);
            startActivity(intent_list);
        }
    }

    private class deleteList implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View v, int position, final long id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(OverviewActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_delete_list, null);

            builder.setView(view);
            final AlertDialog dialog = builder.create();

            // Define views
            Button cancel = view.findViewById(R.id.cancel);
            Button delete = view.findViewById(R.id.delete);

            // cancel button is clicked
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Close dialog
                    dialog.dismiss();
                }
            });

            // delete  button is clicked
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    db.delete(id);
                    loadListsList();

                    Toast.makeText(OverviewActivity.this, "List deleted!", Toast.LENGTH_SHORT).show();

                    // Close dialog
                    dialog.dismiss();
                }
            });
            // Actually display the dialog
            dialog.show();
            return true;
        }
    }
}
