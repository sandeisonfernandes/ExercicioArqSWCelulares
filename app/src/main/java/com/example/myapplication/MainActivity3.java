package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private  Button botao3;
    private  Button botao6;
    private TextView mensagem;
    private static final String[] CONTATOS_PERMISSIONS = {
            Manifest.permission.READ_CONTACTS
    };
    private static final int CONTATOS_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        botao3 = findViewById(R.id.button3);
        botao6 = findViewById(R.id.button6);
        mensagem = findViewById(R.id.textView6);

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","tela 3");
        startService(intent);

        botao3.setOnClickListener(v->{
            Snackbar.make(v,"Não temos a tela 4 por enquanto", Snackbar.LENGTH_LONG).show();
        });

        botao6.setOnClickListener(v->{
            if(podeUsarContatos()){
                List<MyContact> contacts = ContactsHelper.getContacts(this);
                ArrayList<String> nomes = new ArrayList<>();
                if(contacts.size() >=3){
                    for (MyContact contact : contacts) {
                        nomes.add(contact.getName());
                    }

                    acessaRecurso(nomes.get(2));
                }else{
                    mensagem.setText("Não tem três contatos salvos");
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
                if(contacts.size() >=3){
                    for (MyContact contact : contacts) {
                        nomes.add(contact.getName());
                    }
                    acessaRecurso(nomes.get(2));
                }else{
                    mensagem.setText("Não tem três contatos salvos");
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



}