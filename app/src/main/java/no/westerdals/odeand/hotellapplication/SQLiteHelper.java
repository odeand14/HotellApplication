package no.westerdals.odeand.hotellapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Created by Andreas Ødegaard on 22.05.2017.

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_GUESTS = "guests";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_ROOMNUMBER = "roomnumber";
    public static final String COLUMN_PHONENUMBER = "phonenumber";

    public static final String DATABASE_NAME = "highscore1.db";
    public static final int DATABASE_VERSION = 1;
    private static final java.lang.String DATABASE_CREATE =
            "create table " +
                    TABLE_GUESTS + "(" + COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_NAME + " text not null, " + COLUMN_ROOMNUMBER + " integer, " +
                    COLUMN_EMAIL + " text not null, " + COLUMN_PHONENUMBER + " integer not null);";

    public SQLiteHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO make possible to upgrade?

    }
}