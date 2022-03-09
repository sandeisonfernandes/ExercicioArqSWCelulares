package com.example.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;


public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null){
            String name = intent.getStringExtra("TELA");
            Toast.makeText(this, "Nome da Tela: "+name, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("HSS","service on destroy");
    }

}