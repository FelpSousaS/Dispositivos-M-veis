package br.ufc.quixada.scap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.ufc.quixada.scap.auth.FormLogin;

public class TelaAbertura extends AppCompatActivity implements Runnable {
    Thread thread;
    Handler handler;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_abertura);
        handler = new Handler();
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        i=1;
        try{

            while(i<100){
                Thread.sleep(15);
                handler.post(() -> i++);

            }

        }catch (Exception ignored){

        }
        finish();
        startActivity(new Intent(this, FormLogin.class));

    }
}