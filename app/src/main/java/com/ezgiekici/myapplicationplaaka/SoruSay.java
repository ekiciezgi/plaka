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
    private TextView soruSay;
    private TextView soruSaySayac;
    private SeekBar soruSayGosterge;
    private Tercihler tercihler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sorusayisi);
        Button sinaviYenidenBaslat = (Button) findViewById(R.id.sinaviYenidenBaslat);
        Button sinavaGeriDon = (Button) findViewById(R.id.sinavaGeriDon);
        soruSaySayac = (TextView) findViewById(R.id.soruSaySayac);

        soruSayGosterge = (SeekBar) findViewById(R.id.sorusayisiGosterge);
        soruSayGosterge.setOnSeekBarChangeListener(konuDegerDegistir);
        sinaviYenidenBaslat.setOnClickListener(sinaviYenidenBaslatTikla);
        sinavaGeriDon.setOnClickListener(sinavaGeriDonTikla);
        Intent konularIntent = getIntent();
        tercihler = (Tercihler) konularIntent.getSerializableExtra("tercihler");
        /*soruSayGosterge.setProgress(tercihler.getsoruSayisi());
        soruSaySayac.setText(String.format("%d", tercihler.getsoruSayisi()));*/
    }

    public OnClickListener sinaviYenidenBaslatTikla = new OnClickListener() {
        public void onClick(View v) {
            ((SoruSay) v.getContext()).setResult(1);
            finish();

        }
    };
    public OnClickListener sinavaGeriDonTikla = new OnClickListener() {
        public void onClick(View v) {
            ((SoruSay) v.getContext()).setResult(0);
            finish();
        }
    };
    public OnSeekBarChangeListener konuDegerDegistir = new OnSeekBarChangeListener() {
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
