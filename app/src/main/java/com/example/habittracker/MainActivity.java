package com.example.habittracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAdd, btnRead, btnClear;

    DBHelper dbHelper;
    GridLayout gridLayout;
    private LinearLayout.LayoutParams layoutparams;
    private LinearLayout.LayoutParams TextViewparams;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.AddHab);
        btnAdd.setOnClickListener(this::onClick);



        gridLayout = findViewById(R.id.gridLayout);

        dbHelper = new DBHelper(this);



        SQLiteDatabase database = dbHelper.getWritableDatabase();



        String query = "SELECT " + dbHelper.KEY_ID + ", "
                + dbHelper.KEY_TITLE /*+ ", " + dbHelper.KEY_NUMBER_DAYS*/ +  " FROM " + dbHelper.TABLE_CONTACTS;
        Cursor cursor2 = database.rawQuery(query, null);
        if(cursor2.moveToFirst()){
            int idIndex = cursor2.getColumnIndex(DBHelper.KEY_ID);
            int NumberDaysIndex = cursor2.getColumnIndex(DBHelper.KEY_NUMBER_DAYS);
            int titleIndex = cursor2.getColumnIndex(DBHelper.KEY_TITLE);

            do{
                Log.d("mLog",
                        "ID = " + cursor2.getInt(idIndex)
                                + ", title = " + cursor2.getString(titleIndex)
                                + ", NumberDays = " + cursor2.getString(NumberDaysIndex)
                );
                CardView cardView = new CardView(this);
                layoutparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                layoutparams.setMargins(10,10,0,0);
                TextViewparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                TextViewparams.setMargins(10,50,0,0);

                cardView.setId(counter);
                cardView.setLayoutParams(layoutparams);
                cardView.setRadius(15);
                cardView.setPadding(25, 25, 25, 25);
                cardView.setMaxCardElevation(30);
                cardView.setMaxCardElevation(6);
                TextView TitleTV = new TextView(this);
                RadioButton radioButton= new RadioButton(this);


                TitleTV.setLayoutParams(layoutparams);

                TitleTV.setText(cursor2.getString(titleIndex));
                cardView.addView(TitleTV);
                cardView.addView(radioButton);
                gridLayout.setOrientation(GridLayout.VERTICAL);
                gridLayout.addView(cardView);
                counter++;
            } while (cursor2.moveToNext());



        }



    }




    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v){

        switch  (v.getId()){
            case R.id.AddHab:
                Intent intent = new Intent(this, NewHabit.class);
                startActivity(intent);
                finish();
                break;

        }
        dbHelper.close();
    }

}
