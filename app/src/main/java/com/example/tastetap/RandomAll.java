package com.example.tastetap;

import static com.example.tastetap.ConstantsHistoryDB.COL_CAL;
import static com.example.tastetap.ConstantsHistoryDB.COL_NAME;
import static com.example.tastetap.ConstantsHistoryDB.TABLE_NAME;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class RandomAll extends AppCompatActivity {

    int randomIndex;
    Bitmap randomImage;
    String randomName, randomCal;
    ImageView foodPic;
    Window window;
    TextView result_txt;
    Button random_btn, yes_btn;
    FoodDB foodDB;
    HistoryDB historyDB;
    private ArrayList<String> nameList;
    private ArrayList<Integer> calList;
    private ArrayList<Bitmap> imageList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_all);

        if(Build.VERSION.SDK_INT>=21){
            window=getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.my_primary));
        }

        foodDB = new FoodDB(this,"FoodDB.db",1);
        try{
            foodDB.CheckDb();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            foodDB.OpenDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }

        historyDB = new HistoryDB(this);
        yes_btn = findViewById(R.id.yes_button);

        random_btn = findViewById(R.id.random_button);
        result_txt = findViewById(R.id.result_random);
        foodPic = findViewById(R.id.food_view);

        random_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameList = foodDB.fetchAllName();
                calList = foodDB.fetchAllCal();
                imageList = foodDB.getAllImage();
                displayRandomFood();
            }
        });

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(randomName != null) {
                    addEvent(randomName, randomCal);
                }
            }
        });

    }

    private void displayRandomFood() {
        if (!nameList.isEmpty()) {
            getRandomName();
            getRandomCal();
            getRandomImage();

            //display text
            result_txt.setText(randomName + "\n" + randomCal + " kCal");

            //display image
            if (imageList != null) {
                foodPic.setImageBitmap(randomImage);
            }
            foodPic.setContentDescription("Food");

        } else {
            result_txt.setText("Name list is empty");
        }
    }

    public String getRandomName(){
            if (!nameList.isEmpty()) {
                Random random = new Random();
                randomIndex = random.nextInt(nameList.size());
                randomName = nameList.get(randomIndex);
        }
        return randomName;
    }

    public String getRandomCal(){
        randomCal = String.valueOf(calList.get(randomIndex));
        return randomCal;
    }

    public Bitmap getRandomImage(){
        randomImage = imageList.get(randomIndex);
        return randomImage;
    }

    public void addEvent(String name, String cal) {
        SQLiteDatabase db = historyDB.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_CAL, cal);
        db.insert(TABLE_NAME, null, values);
    }

}