package com.example.tastetap;

import static android.provider.BaseColumns._ID;
import static com.example.tastetap.ConstantsHistoryDB.COL_CAL;
import static com.example.tastetap.ConstantsHistoryDB.COL_NAME;
import static com.example.tastetap.ConstantsHistoryDB.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HistoryDB extends SQLiteOpenHelper {
    public HistoryDB(Context ctx){
        super(ctx, "History.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT, "
                + COL_CAL + " TEXT);"  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS History");
        onCreate(db);
    }
}
