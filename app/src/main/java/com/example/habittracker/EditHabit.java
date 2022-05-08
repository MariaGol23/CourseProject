package com.example.habittracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EditHabit extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);
        ImageView imageView3= (ImageView) findViewById(R.id.imageView3);
        imageView3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}