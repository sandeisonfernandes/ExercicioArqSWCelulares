package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button botao2 = findViewById(R.id.button2);
        botao2.setOnClickListener(v->{
            Intent in = new Intent(this, MainActivity3.class);
            startActivity(in);
        });
    }
}