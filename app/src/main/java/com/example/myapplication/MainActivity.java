package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = findViewById(R.id.button);
        botao.setOnClickListener(v->{
            Intent in = new Intent(this, MainActivity2.class);
            startActivity(in);
        });

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","tela 1");
        startService(intent);


    }
}