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

    private static final String DATABASE_POPULATE = "INSERT INTO " + TABLE_GUESTS +
            "(" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_PHONENUMBER + ", " + COLUMN_ROOMNUMBER +")" +
            " VALUES ('Andreas Ødegaard', 'aodegaar@gmail.com', 45453077, 1), " +
            "('Joban', 'odeand@westerdals.no', 45454545, 3), " +
            "('Nils Trulsen', 'andreas.odegaard@outlook.com', 23423423, 6), " +
            "('Kongen Harald', 'odeand14@student.westerdals.no', 12345678, 100), " +
            "('Tor-Morten Grønli', 'tmg@westerdals.no', 12341234, 1337), " +
            "('Lise Norman', 'odeand@westerdals.no', 97657832, 20), " +
            "('Donald Duck', 'odeand@westerdals.no', 13131313, 13)";

    public SQLiteHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DATABASE_POPULATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}