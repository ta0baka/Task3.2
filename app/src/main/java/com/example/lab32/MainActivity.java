package com.example.lab32;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Button viewGroupmatesButton = findViewById(R.id.button_view_groupmates);
        Button addGroupmateButton = findViewById(R.id.button_add_groupmate);
        Button updateGroupmateButton = findViewById(R.id.button_update_groupmate);

        viewGroupmatesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewGroupmatesActivity.class);
                startActivity(intent);
            }
        });

        addGroupmateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String sql = "INSERT INTO " + DatabaseHelper.TABLE_NAME + " (" +
                        DatabaseHelper.COLUMN_SURNAME + ", " +
                        DatabaseHelper.COLUMN_NAME + ", " +
                        DatabaseHelper.COLUMN_PATRONYMIC + ") " +
                        "VALUES ('Новый одногруппник', ' ', ' ');";
                db.execSQL(sql);
            }
        });

        updateGroupmateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE " + DatabaseHelper.TABLE_NAME +
                        " SET " + DatabaseHelper.COLUMN_SURNAME + " = 'Иванов', " +
                        DatabaseHelper.COLUMN_NAME + " = 'Иван', " +
                        DatabaseHelper.COLUMN_PATRONYMIC + " = 'Иванович' " +
                        "WHERE " + DatabaseHelper.COLUMN_ID + " = (SELECT MAX(" + DatabaseHelper.COLUMN_ID + ") FROM " + DatabaseHelper.TABLE_NAME + ");");
            }
        });
    }
}