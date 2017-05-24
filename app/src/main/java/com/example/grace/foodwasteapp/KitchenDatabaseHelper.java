package com.example.grace.foodwasteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by Grace on 5/16/2017.
 */

//interface Serializable allows me to pass objects of KitchenDatabaseHelper between activities
public class KitchenDatabaseHelper extends SQLiteOpenHelper implements Serializable{

    public static final String DATABASE_NAME = "Kitchen.db";
    public static final String TABLE_NAME = "Kitchen_table";
    public static final String INGREDIENT_COLUMN = "INGREDIENT";//column indicating ingredient i.e. tomato
    public static final String QUANTITY_COLUMN = "QUANTITY";//column indicating number of ingredients
    public static final String UNITS_COLUMN = "UNITS";//column indicating units of ingredients i.e. mL or no units

    public KitchenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
//        SQLiteDatabase db = this.getWritableDatabase();
    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        //try {
//            db.execSQL("CREATE TABLE " + TABLE_NAME + " (Ingredient TEXT,Quantity DOUBLE,Units INTEGER);");
////        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
//
//        //}catch (Exception e){}
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS");
//        onCreate(db);
//    }
//
    public boolean insertData(String ingredient, Double quantity, String units){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INGREDIENT_COLUMN,ingredient);
        contentValues.put(QUANTITY_COLUMN,quantity);
        contentValues.put(UNITS_COLUMN,units);
        return -1 != db.insert(TABLE_NAME, null, contentValues);
    }
//
//    public Cursor getAllData(){
//        //the problem is probably here
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
//        return res;//result stores all data
//    }


    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME +" (INGREDIENT STRING,QUANTITY REAL,UNITS TEXT)");

        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + INGREDIENT_COLUMN + " TEXT," +
                QUANTITY_COLUMN + " REAL," + UNITS_COLUMN + " TEXT)";
        db.execSQL(SQL_String);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    public boolean updateData(String ingredient,Double quantity,String units) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INGREDIENT_COLUMN,ingredient);
        contentValues.put(QUANTITY_COLUMN,quantity);
        contentValues.put(UNITS_COLUMN,units);
//        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();

//        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
        return null;
    }
}
