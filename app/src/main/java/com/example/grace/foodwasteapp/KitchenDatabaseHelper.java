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
    public static final String INGREDIENT_COLUMN = "Kitchen.db";//column indicating ingredient i.e. tomato
    public static final String AMOUNT_COLUMN = "Kitchen.db";//column indicating number of ingredients
    public static final String UNITS_COLUMN = "Kitchen.db";//column indicating units of ingredients i.e. mL or no units

    public KitchenDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
