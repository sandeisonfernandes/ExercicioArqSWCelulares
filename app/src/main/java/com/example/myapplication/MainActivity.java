package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Button;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver receiver;

    private  Button botao;
    private  Button botao2;
    private  TextView mensagem;
    private static final String[] CONTATOS_PERMISSIONS = {
      Manifest.permission.READ_CONTACTS
    };
    private static final int CONTATOS_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.button);
        botao2 = findViewById(R.id.button4);
        mensagem = findViewById(R.id.textView4);

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","tela 1");
        startService(intent);

        botao.setOnClickListener(v->{
            Intent in = new Intent(this, MainActivity2.class);
            startActivity(in);
        });


        botao2.setOnClickListener(v->{
            if(podeUsarContatos()){
                List<MyContact> contacts = ContactsHelper.getContacts(this);
                ArrayList<String> nomes = new ArrayList<>();
                if(contacts.size() >=1){
                    for (MyContact contact : contacts) {
                        nomes.add(contact.getName());
                    }

                    acessaRecurso(nomes.get(0));
                }else{
                    mensagem.setText("Não tem contatos salvos");
                }

            }else{
                requestPermissions(CONTATOS_PERMISSIONS,CONTATOS_REQUEST);
                sem_permisão();
            }


            // List<MyContact> contacts = ContactsHelper.getContacts(this);

            //for (MyContact contact : contacts){
            //Log.d("SSF", "ID: " + contact.getId() + ", Name: " + contact.getName());
            // }

        });

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CONTATOS_REQUEST){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                List<MyContact> contacts = ContactsHelper.getContacts(this);
                ArrayList<String> nomes = new ArrayList<>();
                if(contacts.size() >=1){
                    for (MyContact contact : contacts) {
                        nomes.add(contact.getName());
                    }
                    acessaRecurso(nomes.get(0));
                }else{
                    mensagem.setText("Não tem contatos salvos");
                }


            }else {
                mensagem.setText("Não deu Permissão para os contatos");
            }

        }



    }

    private boolean podeUsarContatos(){
        return checkSelfPermission(Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED;
    }

    private void sem_permisão(){
        mensagem.setText(R.string.msg_sem_permissao);
    }

    private void acessaRecurso(String resultadoID){
        mensagem.setText(resultadoID);

    }


    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver = null ;
        }
    }

    // Nova forma de criar Broadcast
    class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("SSF", "O Status do Wi-Fi Mudou");
        }
    }


}