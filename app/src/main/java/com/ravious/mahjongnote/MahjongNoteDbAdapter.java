package com.ravious.mahjongnote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.BaseAdapter;

import java.util.Date;

/**
 * Created by yuuki on 7/17/16.
 */

public class MahjongNoteDbAdapter {

    public static final String COL_GAME_PAYMENT1 = "game_payment";
    static final String DATABASE_NAME = "mayjong_note.db";
    static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "total_notes";
    public static final String COL_DATE = "date";
    public static final String COL_TOTAL_DIVIDENT = "total_divident";
    public static final String COL_GAME_PAYMENT = "game_payment";
    public static final String COL_NUMBER_OF_GAMES = "number_of_games";
    public static final String COL_AVERAGE_RANK = "average_rank";
    public static final String COL_FIRST = "first";
    public static final String COL_SECOND = "second";
    public static final String COL_THIRD = "third";
    public static final String COL_FOURTH = "fourth";
    public static final String COL_FIRST_PLAYER = "first_player";
    public static final String COL_SECOND_PLAYER = "second_player";
    public static final String COL_THIRD_PLAYER = "third_player";
    public static final String COL_FOURTH_PLAYER = "fourth_player";

    protected final Context context;
    protected DatabaseHelper dbHelper;
    protected SQLiteDatabase db;


    public MahjongNoteDbAdapter(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(this.context);
    }

    // SQLiteOpenHelper
    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "
                    + TABLE_NAME + " ("
                    + COL_DATE + " TEXT NOT NULL,"
                    + COL_TOTAL_DIVIDENT + " TEXT NOT NULL,"
                    + COL_GAME_PAYMENT + " TEXT NOT NULL,"
                    + COL_NUMBER_OF_GAMES + " TEXT NOT NULL,"
                    + COL_AVERAGE_RANK + " TEXT NOT NULL,"
                    + COL_FIRST + " TEXT NOT NULL,"
                    + COL_SECOND + " TEXT NOT NULL,"
                    + COL_THIRD + " TEXT NOT NULL,"
                    + COL_FOURTH + " TEXT NOT NULL,"
                    + COL_FIRST_PLAYER + " TEXT NOT NULL,"
                    + COL_SECOND_PLAYER + " TEXT NOT NULL,"
                    + COL_THIRD_PLAYER + " TEXT NOT NULL,"
                    + COL_FOURTH_PLAYER + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    // Adapter Methods
    public MahjongNoteDbAdapter open() {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // App Methods
    public boolean deleteAllNotes() {
        return db.delete(TABLE_NAME, null, null) > 0;
    }

    public boolean deleteNote(int date) {
        return db.delete(TABLE_NAME, COL_DATE + "=" + date, null) > 0;
    }

    public Cursor getAllNotes() {
        return db.query(TABLE_NAME, null, null, null, null, null, null);
    }

    public void saveData(String date, String total_divident, String game_payment,
                         String number_of_games, String average_rank, String first,
                         String second, String third, String fourth, String first_player,
                         String second_player, String third_player, String fourth_player) {
        Date dateNow = new Date();
        ContentValues values = new ContentValues();
        values.put(COL_DATE, dateNow.toString());
        values.put(COL_TOTAL_DIVIDENT, total_divident);
        values.put(COL_GAME_PAYMENT, game_payment);
        values.put(COL_NUMBER_OF_GAMES, number_of_games);
        values.put(COL_AVERAGE_RANK, average_rank);
        values.put(COL_FIRST, first);
        values.put(COL_SECOND, second);
        values.put(COL_THIRD, third);
        values.put(COL_FOURTH, fourth);
        values.put(COL_FIRST_PLAYER, first_player);
        values.put(COL_SECOND_PLAYER, second_player);
        values.put(COL_THIRD_PLAYER, third_player);
        values.put(COL_FOURTH_PLAYER, fourth_player);

        db.insertOrThrow(TABLE_NAME, null, values);
    }


}
