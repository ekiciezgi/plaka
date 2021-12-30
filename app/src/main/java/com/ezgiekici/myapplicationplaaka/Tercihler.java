package com.ezgiekici.myapplicationplaaka;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Tercihler implements Serializable{
    private int soruSayisi;
    public Tercihler(int soruSayisi){
        this.soruSayisi = soruSayisi;
    }
    public int getsoruSayisi(){
        return soruSayisi;
    }

}
