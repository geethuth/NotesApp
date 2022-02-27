package com.example.notesapp.model;

public class Notes {
    public static final String table_name = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTES = "note";
    public static final String COLUMN_TIMESTAMP = "time";

    int id;
    String notes;
    String timeStamp;
    public static final String CREATE_TABLE ="CREATE TABLE "+table_name+" ("
           + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NOTES +" TEXT,"
            + COLUMN_TIMESTAMP +" DATETIME DEFAULT CURRENT_TIMESTAMP)";


    public Notes(int id, String notes, String timeStamp) {
        this.id = id;
        this.notes = notes;
        this.timeStamp = timeStamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }


}
