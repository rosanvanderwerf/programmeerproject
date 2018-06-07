package com.example.rosan.project;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "lists.db";
    private static final int DATABASE_VERSON = 1;

    private static final String KEY_ID = "_id";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_LISTNAME = "listname";
    private static final String KEY_WORDS = "words";

    private static final String TABLE = "ListTable";
    private static ListDatabase instance = null;

    // Private constructor, using SQLite
    public ListDatabase(Context c){
        super(c,DATABASE_NAME,null,DATABASE_VERSON);
    }

    public static ListDatabase getInstance(Context c){
        if (instance == null){
            instance = new ListDatabase(c);
        }
        return instance;
    }

    // Create database upon opening the app (for the first time)
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_DB = "CREATE TABLE " + TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_LISTNAME + " TEXT NOT NULL, " + KEY_WORDS + " TEXT NOT NULL, "
                + KEY_TIMESTAMP + "DATETIME DEFAULT CURRENT_TIMESTAMP);";
        sqLiteDatabase.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // selectAll used to fill ListView in OverviewActivity
    public Cursor selectAll(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE;
        Cursor cursor = db.rawQuery(query,null);
        db.close();
        return cursor;
    }

    // insert new list (in OverviewActivity)
    /*
    * public void insert(JournalEntry entry){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, entry.getTitle());
        values.put(KEY_CONTENT, entry.getContent());
        values.put(KEY_MOOD, entry.getMood());
        sqLiteDatabase.insert(TABLE,null,values);
        sqLiteDatabase.close();
    }

    public void delete(long id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE,KEY_ID + "=" + id,null);
        sqLiteDatabase.close();
    } */



}
