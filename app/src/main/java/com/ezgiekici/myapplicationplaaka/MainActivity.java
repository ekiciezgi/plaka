package com.ezgiekici.myapplicationplaaka;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    String cities[] = {" ","Adana","Adıyaman","Afyon","Ağrı","Amasya","Ankara","Antalya","Artvin","Aydın","Balıkesir","Bilecik","Bingöl","Bitlis","Bolu","Burdur","Bursa","Çanakkale"
            ,"Çankırı","Çorum","Denizli","Diyarbakır","Edirne","Elazığ","Erzincan","Erzurum","Eskişehir","Gaziantep","Giresun","Gümüşhane","Hakkari","Hatay","Isparta","Mersin","İstanbul"
            ,"İzmir","Kars","Kastamonu","Kayseri","kırklareli","Kırşehir","Kocaeli","Konya","Kütahya","Malatya","Manisa","KahramanMaraş","Mardin","Muğla","Muş","Nevşehir","Niğde","Ordu"
            ,"Rize","Sakarya","Samsun","Siirt","Sinop","Sivas","Tekirdağ","Tokat","Trabzon","Tunceli","Şanlıurfa","Uşak","Van","Yozgat","Zonguldak","Aksaray","Bayburt","Karaman"
            ,"Kırıkkale","Batman","Şırnak","Bartın","Ardahan","Iğdır","Yalova","Karabük","Kilis","Osmaniye","Düzce"};

    String correctAnswer = " ";
    int correctAnswerInt = 0;

    ArrayList<String> selectedCities;
    ArrayList<Integer> used;
    private int soruSayisi;
    TextView question;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    RadioButton rb5;
    RadioGroup rg;
    Button startButton;
    Button cevaplaButton;
    Button successButton;
    Button nextButton;
    Button sorular;
    int a;
    int sayac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.questionText);
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);
        rg = findViewById(R.id.radioGroup);
        startButton = findViewById(R.id.startButton);
        cevaplaButton = findViewById(R.id.cevaplaButton);
        successButton = findViewById(R.id.successButton);
        nextButton = findViewById(R.id.nextButton);
        sorular = (Button) findViewById(R.id.SoruSayButton);
        used = new ArrayList<Integer>();
        selectedCities = new ArrayList<String>();

        cevaplaButton.setVisibility(View.INVISIBLE);
        rg.setVisibility(View.INVISIBLE);
        successButton.setVisibility(View.INVISIBLE);
        nextButton.setVisibility(View.INVISIBLE);
        sorular.setOnClickListener(soruTikla);
        startButton.setOnClickListener(sinaviBaslatTikla);
        sayac = 0;
        a = 0;
        Intent i = getIntent();
        a = i.getIntExtra("key", 0);
        soruSayisi = i.getIntExtra("soruSayisi", 100000);


        if(a == 1) {
            Basla();
        }
    }

    public void Basla(){
        startButton.setVisibility(View.INVISIBLE);
        rg.setVisibility(View.VISIBLE);
        cevaplaButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        sorular.setVisibility(View.INVISIBLE);
                Random random = new Random();
            int rnd = random.nextInt(81) + 1;
            correctAnswerInt = rnd;
            correctAnswer = cities[rnd];
            selectedCities.add(cities[rnd]);

            for(int i = 1; i<5; i++) {
                while(selectedCities.contains(cities[rnd])){
                    rnd = random.nextInt(81) + 1;
                }
                selectedCities.add(cities[rnd]);
            }

            question.setText(correctAnswerInt + " Plakalı İlimiz Hangisidir?");

            rnd = random.nextInt(5);
            used.add(rnd);
            rb1.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb2.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb3.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb4.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb5.setText(selectedCities.get(rnd));

            selectedCities.clear();
            used.clear();
        }

    public OnClickListener sinaviBaslatTikla = new OnClickListener() {
        public void onClick(View v){
            Basla();
        }
    };

    public OnClickListener soruTikla = new OnClickListener() {
        public void onClick(View v){
            Intent soruIntent = new Intent();
            soruIntent.setClass(MainActivity.this,SoruSay.class);
            startActivity(soruIntent);
        }
    };
    public void Next(View view) {
        if(sayac < soruSayisi-1) {
            successButton.setVisibility(View.INVISIBLE);
            Random random = new Random();
            int rnd = random.nextInt(81) + 1;
            correctAnswerInt = rnd;
            correctAnswer = cities[rnd];
            selectedCities.add(cities[rnd]);

            for(int i = 1; i<5; i++) {
                while(selectedCities.contains(cities[rnd])){
                    rnd = random.nextInt(81) + 1;
                }
                selectedCities.add(cities[rnd]);
            }

            question.setText(correctAnswerInt + " Plakalı İlimiz Hangisidir?");

            rnd = random.nextInt(5);
            used.add(rnd);
            rb1.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb2.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb3.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb4.setText(selectedCities.get(rnd));

            while(used.contains(rnd)) {
                rnd = random.nextInt(5);
            }
            used.add(rnd);
            rb5.setText(selectedCities.get(rnd));

            selectedCities.clear();
            used.clear();

            rg.clearCheck();

            sayac++;
        }else {
            cevaplaButton.setVisibility(View.INVISIBLE);
            rg.setVisibility(View.INVISIBLE);
            successButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
            question.setText( "PLAKALAR SINAVI BİTTİ");
        }

    }

    public void Cevapla(View view) {
        boolean successfull = false;

        String chosenAnswer = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        if(correctAnswer == chosenAnswer) {
            successfull = true;
        }

        if(successfull == true) {
            successButton.setTextColor(Color.GREEN);
            successButton.setText("Doğru");
        } else {
            successButton.setTextColor(Color.RED);
            successButton.setText("Yanlış");
        }
        successButton.setVisibility(View.VISIBLE);
    }
}