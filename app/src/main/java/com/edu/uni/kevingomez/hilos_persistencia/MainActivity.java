package com.edu.uni.kevingomez.hilos_persistencia;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ayuda(View vista){
        Intent intent = new Intent(this,Ayuda_Actividad.class);
        startActivity(intent);
    }

    public void dificultad(View vista){
        String dific = (String) ((Button) vista).getText();
        int dificultad = 1;
        if(dific.equals(getString(R.string.medio))) dificultad=2;
        if(dific.equals(getString(R.string.dificil))) dificultad=3;

        Intent in = new Intent(this, Gestion.class);
        in.putExtra("DIFICULTAD",dificultad);

        startActivityForResult(in,1);

    }

    protected void onActivityResult(int peticion, int codigo, Intent puntuacion){
        if(peticion!=1||codigo!=RESULT_OK) return;
        int resultado = puntuacion.getIntExtra("PUNTUACION",0);

        if(resultado>record){
            record=resultado;
            TextView caja = (TextView) findViewById(R.id.record);
            caja.setText("Record: "+record);
            guardaRecord();
        }else{
            String puntuacionPartida = "" + resultado;
            Toast mitoast = Toast.makeText(this,puntuacionPartida,Toast.LENGTH_SHORT);
            mitoast.setGravity(Gravity.CENTER,0,0);
            mitoast.show();
        }

    }

    public void onResume() {

        super.onResume();
        leerecord();
    }

    private void guardaRecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor mieditor = datos.edit();
        mieditor.putInt("RECORD",record);
        mieditor.apply();
    }

    private void leerecord(){
        SharedPreferences datos = PreferenceManager.getDefaultSharedPreferences(this);
        record = datos.getInt("RECORD", 0);
        TextView caja = (TextView) findViewById(R.id.record);
        caja.setText("Record: "+record);
    }
}
