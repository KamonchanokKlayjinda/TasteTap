package com.example.tastetap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;

public class HistoryView extends AppCompatActivity {
    Window window;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);

        if(Build.VERSION.SDK_INT>=21){
            window=getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.my_primary));
        }

    }
}