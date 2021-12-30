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
    private Tercihler tercihler;
    private int soruSayisi;
    static final int YENIDEN_BASLAT = 1;
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
    }
    public void Basla(){
        startButton.setVisibility(View.INVISIBLE);
        rg.setVisibility(View.VISIBLE);
        cevaplaButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
        sorular.setVisibility(View.INVISIBLE);
        for(int k=0;k<soruSayisi;k++)
        {        Random random = new Random();
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

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == YENIDEN_BASLAT) {
            if (resultCode == 1) {
                tercihleriOku();
                Basla();
            }
        }
    }
    public OnClickListener sinaviBaslatTikla = new OnClickListener() {
        public void onClick(View v){
          Basla();
        }
    };
    private void tercihleriOku(){
        SharedPreferences temelTercihler = getSharedPreferences("EhliyetSinavi", MODE_PRIVATE);
        tercihler = new Tercihler( temelTercihler.getInt("soruSayisi", 10));
    }
    public OnClickListener soruTikla = new OnClickListener() {
        public void onClick(View v){
            Intent soruIntent = new Intent();
            soruIntent.putExtra("tercihler", tercihler);
            soruIntent.setClass(MainActivity.this,SoruSay.class);
            startActivity(soruIntent);
        }
    };
    public void Next(View view) {
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

    public void Start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        rg.setVisibility(View.VISIBLE);
        cevaplaButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.VISIBLE);
       sorular.setVisibility(View.INVISIBLE);
        for(int k=0;k<soruSayisi;k++)
        {        Random random = new Random();
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
}

}