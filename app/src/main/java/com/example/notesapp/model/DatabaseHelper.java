package com.example.notesapp.model;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notes_db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Notes.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Notes.table_name);
    }

    public long insertNotes(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Notes.COLUMN_NOTES, note);
        long rowid = db.insert(Notes.table_name, null, contentValues);
        db.close();
        return rowid;
    }

    @SuppressLint("Range")
    public Notes getNote(long id) {
        Notes notes = null;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + Notes.table_name + " WHERE id=" + id + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            notes = new Notes(cursor.getInt(cursor.getColumnIndex(Notes.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(Notes.COLUMN_NOTES)),
                    cursor.getString(cursor.getColumnIndex(Notes.COLUMN_TIMESTAMP)));

            cursor.close();
        }
        sqLiteDatabase.close();
        return notes;
    }

    public List<Notes> getAllNotes() {
        List<Notes> allNotes = new ArrayList<>();
        SQLiteDatabase sd = this.getWritableDatabase();
        String query = "SELECT * FROM " + Notes.table_name + ";";
        Cursor cursor = sd.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") Notes notes = new Notes(cursor.getInt(cursor.getColumnIndex(Notes.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(Notes.COLUMN_NOTES)),
                        cursor.getString(cursor.getColumnIndex(Notes.COLUMN_TIMESTAMP)));
                allNotes.add(notes);
            }
            cursor.close();
        }
        sd.close();
        return allNotes;
    }

    public int getNotesCount() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from " + Notes.table_name + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public int updateNotes(Notes note) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Notes.COLUMN_NOTES, note.getNotes());

        return sqLiteDatabase.update(Notes.table_name, values, Notes.COLUMN_ID + "=?", new String[]
                {String.valueOf(note.getId())});
    }

    public void deleteUserNotes(Notes notes){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(Notes.table_name, Notes.COLUMN_ID + "=?", new String[]
                {String.valueOf(notes.getId())});
    }
}
