package com.example.tastetap;

import static android.provider.BaseColumns._ID;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.tastetap.ConstantsHistoryDB.COL_CAL;
import static com.example.tastetap.ConstantsHistoryDB.COL_NAME;
import static com.example.tastetap.ConstantsHistoryDB.TABLE_NAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class HistoryView extends AppCompatActivity {
    Window window;
    HistoryDB historyDB;
    Button reset_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_view);

        historyDB = new HistoryDB(this);

        if(Build.VERSION.SDK_INT>=21){
            window=getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.my_primary));
        }
        Cursor cursor = getEvents();
        showEvents(cursor);

        reset_Button = findViewById(R.id.reset_button);
        reset_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAutoInc();
                Cursor cursor = getEvents();
                showEvents(cursor);
            }
        });
    }
    private void showEvents(Cursor cursor) {
        final ListView listView = (ListView)findViewById(R.id.listview_history);
        final ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;
        while(cursor.moveToNext()) {
            map = new HashMap<String, String>();
            map.put("Id", String.valueOf(cursor.getLong(0)));
            map.put("Name", cursor.getString(1));
            map.put("Calories", cursor.getString(2));
            MyArrList.add(map);
        }
        SimpleAdapter sAdap;
        sAdap = new SimpleAdapter( this, MyArrList, R.layout.column_food,
                new String[] {"Id", "Name", "Calories"},
                new int[] {R.id.col_row_id, R.id.col_row_name, R.id.col_row_cal} );
        listView.setAdapter(sAdap);
    }//end showEvents

    private Cursor getEvents() {
        String[] FROM = {_ID, COL_NAME, COL_CAL};
        String ORDER_BY = _ID + " DESC";
        SQLiteDatabase db = historyDB.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM, null, null, null, null, ORDER_BY);
        return cursor;
    }//end getEvents

    private void resetAutoInc() {
        SQLiteDatabase db = historyDB.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_NAME + "'");
    }

}