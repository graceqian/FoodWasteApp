package com.example.grace.foodwasteapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Grace on 5/16/2017.
 */

public class KitchenDatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Kitchen.db";
    public static final String TABLE_NAME = "Kitchen_table.db";
    public static final String INGREDIENT_COLUMN = "Ingredient";//column indicating ingredient i.e. tomato
    public static final String AMOUNT_COLUMN = "Amount";//column indicating number of ingredients
    public static final String UNITS_COLUMN = "Units";//column indicating units of ingredients i.e. mL or no units

    public KitchenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABLE_NAME + "Ingredient TEXT, Amount INTEGER, Units INTEGER");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS");
        onCreate(db);
    }
}
