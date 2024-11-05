package com.example.lab32;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

public class DatabaseHelper extends android.database.sqlite.SQLiteOpenHelper{
    private static final String DATABASE_NAME = "group.db";
    private static final int DATABASE_VERSION = 3;
    public static final String TABLE_NAME = "groupmates";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PATRONYMIC = "patronymic";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SURNAME + " TEXT NOT NULL, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_PATRONYMIC + " TEXT NOT NULL, " +
                    COLUMN_TIMESTAMP + " TIME DEFAULT CURRENT_TIME" + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        initializeData(db);
    }
    private void initializeData(SQLiteDatabase db) {
        db.execSQL("DELETE FROM " + TABLE_NAME);
        String[] fullNames = {
                "Новикова Анастасия Евгеньевна",
                "Петров Пётр Петрович",
                "Сидоров Сидор Сидорович",
                "Никитин Никита Никитич",
                "Сергеев Сергей Сергеевич"
        };
        for (String fullName : fullNames) {
            String[] nameParts = fullName.split(" ");

            if (nameParts.length == 3) {
                String surname = nameParts[0];
                String name = nameParts[1];
                String patronymic = nameParts[2];

                ContentValues values = new ContentValues();
                values.put(COLUMN_SURNAME, surname);
                values.put(COLUMN_NAME, name);
                values.put(COLUMN_PATRONYMIC, patronymic);

                db.insert(TABLE_NAME, null, values);
            }
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}