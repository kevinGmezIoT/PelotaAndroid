package com.edu.uni.kevingomez.hilos_persistencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Ayuda_Actividad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda__actividad);
    }

    public void  volver(View vista){
        onBackPressed();
    }
}
