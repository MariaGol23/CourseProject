package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class NewHabit extends AppCompatActivity implements View.OnClickListener {
    DBHelper dbHelper;
    EditText TitleEt;
    Button Save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);
        TitleEt = findViewById(R.id.TitleInput);
        Save = findViewById(R.id.SaveBtn);
        Save.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String title = TitleEt.getText().toString();
        contentValues.put(DBHelper.KEY_TITLE, title);
        database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
        Log.d("mLog",  "Added: title = " + title);
        Intent i = new Intent(this, MainActivity.class);
        finish();
    }

}