package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button botao3 = findViewById(R.id.button3);
        botao3.setOnClickListener(v->{
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        });
    }
}