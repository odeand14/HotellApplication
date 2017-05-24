package no.westerdals.odeand.hotellapplication;

// Created by Andreas Ødegaard on 22.05.2017.


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class GuestsDataSource {

    private SQLiteHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allColumns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_NAME,
            SQLiteHelper.COLUMN_ROOMNUMBER, SQLiteHelper.COLUMN_EMAIL, SQLiteHelper.COLUMN_PHONENUMBER};

    public GuestsDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Guest createGuest(Guest guest) {
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.COLUMN_NAME, guest.getName());
        values.put(SQLiteHelper.COLUMN_ROOMNUMBER, guest.getRoomNumber());
        values.put(SQLiteHelper.COLUMN_EMAIL, guest.getEmail());
        values.put(SQLiteHelper.COLUMN_PHONENUMBER, guest.getPhoneNumber());

        long insertID = database.insert(SQLiteHelper.TABLE_GUESTS, null, values);

        Cursor cursor = database.query(SQLiteHelper.TABLE_GUESTS, allColumns,
                SQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null, null);

        cursor.moveToFirst();
        Guest newGuest = cursorToGuest(cursor);
        cursor.close();

        return newGuest;
    }

    private Guest cursorToGuest(Cursor cursor) {
        return new Guest(
                cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_EMAIL)),
                Long.parseLong(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID))),
                Long.parseLong(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_PHONENUMBER))),
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ROOMNUMBER))));
    }

    public List<Guest> getAllGuests() {
        List<Guest> allGuests = new ArrayList<>();
        Cursor cursor = database.query(SQLiteHelper.TABLE_GUESTS, allColumns,
                null, null, null, null, SQLiteHelper.COLUMN_ROOMNUMBER + " DESC ");

        cursor.moveToFirst();
        allGuests.add(cursorToGuest(cursor));
        while (cursor.moveToNext()) {
            allGuests.add(cursorToGuest(cursor));
        }
        cursor.close();
        return allGuests;
    }

    public Guest getGuest(int roomNumber) {
        Guest guest = null;
        try (Cursor cursor = database.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_GUESTS + " WHERE roomnumber=?", new String[]{roomNumber + ""})) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                guest = cursorToGuest(cursor);
            }
            return guest;
        }
    }



}
