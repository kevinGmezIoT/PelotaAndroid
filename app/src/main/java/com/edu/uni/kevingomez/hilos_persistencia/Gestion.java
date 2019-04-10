package com.edu.uni.kevingomez.hilos_persistencia;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

public class Gestion extends Activity {
    private Partida partida;
    private int dificultad;
    private int FPS = 30;
    private Handler temporizador;
    private int botes;

    protected void onCreate(Bundle savedInstancestate) {
        super.onCreate(savedInstancestate);
        botes = 0; //Inicializar variable
        Bundle extras = getIntent().getExtras();
        dificultad = extras.getInt("DIFICULTAD");
        partida = new Partida(getApplicationContext(), dificultad);
        setContentView(partida);

        temporizador = new Handler();
        temporizador.postDelayed(hilo,2000);
        }

        private Runnable hilo = new Runnable(){
        @Override
            public void run(){
            if(partida.movimientoBola()) fin();
            else {
                partida.invalidate();
                temporizador.postDelayed(hilo, 1000 / FPS);
            }
        }
    };

    public boolean onTouchEvent(MotionEvent evento){
        int x = (int) evento.getX();
        int y = (int) evento.getY();
        if(partida.toque(x,y)) botes++;

        return false;
    }

    public void fin(){
        temporizador.removeCallbacks(hilo);

        Intent in = new Intent();
        in.putExtra("PUNTUACION",botes);

        setResult(RESULT_OK,in);
        finish();
    }
}
