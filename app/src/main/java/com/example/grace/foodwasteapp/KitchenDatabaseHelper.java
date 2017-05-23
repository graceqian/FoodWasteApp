package com.example.grace.foodwasteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

/**
 * Created by Grace on 5/16/2017.
 */

//interface Serializable allows me to pass objects of KitchenDatabaseHelper between activities
public class KitchenDatabaseHelper extends SQLiteOpenHelper implements Serializable{

    public static final String DATABASE_NAME = "Kitchen.db";
    public static final String TABLE_NAME = "Kitchen_table.db";
    public static final String INGREDIENT_COLUMN = "Ingredient";//column indicating ingredient i.e. tomato
    public static final String QUANTITY_COLUMN = "Quantity";//column indicating number of ingredients
    public static final String UNITS_COLUMN = "Units";//column indicating units of ingredients i.e. mL or no units

    public KitchenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (Ingredient TEXT, Quantity INTEGER, Units INTEGER);");
        }catch (Exception e){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS");
        onCreate(db);
    }

    public boolean insertData(String ingredient, Double quantity, String units){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INGREDIENT_COLUMN,ingredient);
        contentValues.put(QUANTITY_COLUMN,quantity);
        contentValues.put(UNITS_COLUMN,units);
        return -1 != db.insert(TABLE_NAME, null, contentValues);
    }
}
