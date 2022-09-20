package br.quixada.ufc.appcomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity implements Runnable{

    Thread thread;
    Handler handler;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        thread = new Thread(this);
        thread.start();
        getSupportActionBar().hide();
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
        startActivity(new Intent(this, TelaLogin.class));
    }
}