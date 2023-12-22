package com.example.tastetap;

import static android.content.ContentValues.TAG;
import static com.example.tastetap.ConstantsFoodDB.COL_CAL;
import static com.example.tastetap.ConstantsFoodDB.COL_CATEGORY;
import static com.example.tastetap.ConstantsFoodDB.COL_ID;
import static com.example.tastetap.ConstantsFoodDB.COL_NAME;
import static com.example.tastetap.ConstantsFoodDB.COL_PIC;
import static com.example.tastetap.ConstantsFoodDB.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class FoodDB extends SQLiteOpenHelper {

    String DB_NAME;
    String DB_PATH;
    Context mcontext;


    public FoodDB(Context context, String name, int version) {
        super(context, name, null, version);
        this.DB_NAME = name;
        this.mcontext = context;
        this.DB_PATH = "/data/data/" + "com.example.tastetap" + "/databases/";
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void CheckDb(){
        SQLiteDatabase checkDb = null;
        String filePath = DB_PATH + DB_NAME;
        try{
            checkDb = SQLiteDatabase.openDatabase(filePath,null,0);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(checkDb != null){
            Toast.makeText(mcontext, "Database already exists", Toast.LENGTH_SHORT).show();
        }else {
            CopyDatabase();
        }
    }

    public  void CopyDatabase(){
        this.getReadableDatabase();

        try{
            InputStream input = mcontext.getAssets().open(DB_NAME);
            OutputStream output = new FileOutputStream(DB_PATH + DB_NAME);

            byte[] buffer = new byte[1024];

            int len;
            while((len = input.read(buffer))>0){
                output.write(buffer,0,len);
            }
            output.flush();
            input.close();
            output.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("CopyDb","Database Copied");
    }

    public void OpenDatabase(){
        String filePath = DB_PATH + DB_NAME;
        SQLiteDatabase.openDatabase(filePath,null,0);
    }

    //*************** fetch food image ***************
    public ArrayList<Bitmap> getAllImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getThaiImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารไทย'";;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getJapaneseImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารญี่ปุ่น'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getKoreanImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารเกาหลี'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getChineseImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารจีน'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getIndianImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารอินเดีย'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getWesternImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารตะวันตก'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getDrinkImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'เครื่องดื่ม'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    public ArrayList<Bitmap> getDessertImage() {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT " + COL_PIC + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'ของหวาน'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex(COL_PIC));
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imageList.add(bitmap);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return imageList;
    }

    //*************** fetch food id ***************
    public ArrayList<Integer> fetchID() {
        ArrayList<Integer> idList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                Log.d(TAG, "fetchData: ID = " + id);
                idList.add(id);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return idList;
    }

    //*************** fetch food cal ***************
    public ArrayList<Integer> fetchAllCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchThaiCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารไทย'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchJapaneseCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารญี่ปุ่น'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchKoreanCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารเกาหลี'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchChineseCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารจีน'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchIndianCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารอินเดีย'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchWesternCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารตะวันตก'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchDrinkCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'เครื่องดื่ม'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    public ArrayList<Integer> fetchDessertCal() {
        ArrayList<Integer> calList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_CAL + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'ของหวาน'";
        Cursor cursor = db.rawQuery(query, null);


        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int cal = cursor.getInt(cursor.getColumnIndex(COL_CAL));
                Log.d(TAG, "fetchData: Cal = " + cal);
                calList.add(cal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return calList;
    }

    //*************** fetch food name ***************
    public ArrayList<String> fetchAllName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchThaiName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารไทย'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchJapaneseName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารญี่ปุ่น'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchKoreanName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารเกาหลี'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchChineseName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารจีน'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchIndianName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารอินเดีย'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchWesternName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'อาหารตะวันตก'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchDrinkName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'เครื่องดื่ม'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

    public ArrayList<String> fetchDessertName() {
        ArrayList<String> nameList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_NAME + " FROM " + TABLE_NAME + " WHERE " + COL_CATEGORY + " = 'ของหวาน'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
                Log.d(TAG, "fetchData: Name = " + name);
                nameList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return nameList;
    }

}
