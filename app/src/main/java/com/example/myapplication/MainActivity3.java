package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button botao3 = findViewById(R.id.button3);
        botao3.setOnClickListener(v->{
            Snackbar.make(v,"Não temos a tela 4 por enquanto", Snackbar.LENGTH_LONG).show();
        });

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","tela 3");
        startService(intent);
    }
}