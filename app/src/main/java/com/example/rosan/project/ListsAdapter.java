package com.example.rosan.project;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;

public class ListsAdapter extends ResourceCursorAdapter {

    public ListsAdapter(Context context, Cursor c) {
        super(context, R.layout.list_row, c);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Extract data from cursor
        String list_name = cursor.getString(cursor.getColumnIndex("list_name"));
        String timestamp = (Timestamp.valueOf(cursor.getString(2))).toString();

        // Find views
        TextView tv_list_name = view.findViewById(R.id.list_name);
        TextView tv_timestamp = view.findViewById(R.id.timestamp);

        // Set name and date
        tv_list_name.setText(list_name);
        tv_timestamp.setText(timestamp);
    }
}
