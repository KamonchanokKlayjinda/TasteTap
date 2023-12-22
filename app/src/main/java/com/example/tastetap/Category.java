package com.example.tastetap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toolbar;

public class Category extends AppCompatActivity {

    CardView ThaiCat, JapaneseCat, IndianCat, KoreanCat, ChineseCat, WesternCat, DrinkCat, DessertCat, AllCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        if(Build.VERSION.SDK_INT>=21){
            Window window = getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.my_primary));
        }

        ThaiCat = findViewById(R.id.thai);
        JapaneseCat = findViewById(R.id.japanese);
        IndianCat = findViewById(R.id.indian);
        WesternCat = findViewById(R.id.western);
        KoreanCat = findViewById(R.id.korean);
        ChineseCat = findViewById(R.id.chinese);
        DrinkCat = findViewById(R.id.drink);
        DessertCat = findViewById(R.id.dessert);

        ThaiCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomThai.class);
                startActivity(intent);
            }
        });

        JapaneseCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomJapanese.class);
                startActivity(intent);
            }
        });

        IndianCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomIndian.class);
                startActivity(intent);
            }
        });

        WesternCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomWestern.class);
                startActivity(intent);
            }
        });

        KoreanCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomKorean.class);
                startActivity(intent);
            }
        });

        ChineseCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomChinese.class);
                startActivity(intent);
            }
        });

        DrinkCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomDrink.class);
                startActivity(intent);
            }
        });

        DessertCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Category.this,RandomDessert.class);
                startActivity(intent);
            }
        });

    }
}