package com.ezgiekici.myapplicationplaaka;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.content.Intent;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.SeekBar;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SoruSay extends AppCompatActivity {

    private TextView soruSaySayac;
    private SeekBar soruSayGosterge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorusayisi);
        Button sinaviYenidenBaslat = (Button) findViewById(R.id.sinaviYenidenBaslat);
        Button sinavaGeriDon = (Button) findViewById(R.id.sinavaGeriDon);
        soruSaySayac = (TextView) findViewById(R.id.soruSaySayac);

        soruSayGosterge = (SeekBar) findViewById(R.id.sorusayisiGosterge);
        soruSayGosterge.setOnSeekBarChangeListener(soruSayisiDegistir);
        sinaviYenidenBaslat.setOnClickListener(sinaviYenidenBaslatTikla);
        sinavaGeriDon.setOnClickListener(anasayfayaGeriDonTikla);

    }

    public OnClickListener sinaviYenidenBaslatTikla = new OnClickListener() {
        public void onClick(View v) {
            Intent yenidenBaşlatIntent = new Intent(SoruSay.this, MainActivity.class);
            yenidenBaşlatIntent.putExtra("key", 1);
            yenidenBaşlatIntent.putExtra("soruSayisi", soruSayGosterge.getProgress());
            startActivity(yenidenBaşlatIntent);
        }
    };
    public OnClickListener anasayfayaGeriDonTikla = new OnClickListener() {
        public void onClick(View v) {
           finish();
        }
    };

    public OnSeekBarChangeListener soruSayisiDegistir = new OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
            SharedPreferences temelTercihler = getSharedPreferences("plakalar", MODE_PRIVATE);
            SharedPreferences.Editor temelTercihlerDegistir = temelTercihler.edit();
            if (((String) arg0.getTag()).equalsIgnoreCase("1")) {
                temelTercihlerDegistir.putInt("SoruSayisi", arg1);
                soruSaySayac.setText(String.format("%d", arg1));
            }

            temelTercihlerDegistir.apply();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


}
