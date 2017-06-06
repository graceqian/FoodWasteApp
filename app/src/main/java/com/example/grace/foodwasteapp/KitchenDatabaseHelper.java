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
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_String = "CREATE TABLE " + TABLE_NAME + "(" + INGREDIENT_COLUMN + " TEXT," +
                QUANTITY_COLUMN + " REAL," + UNITS_COLUMN + " TEXT)";
        db.execSQL(SQL_String);

    }

    //insert data into the kitchen database
    public boolean insertData(String ingredient, Double quantity, String units){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INGREDIENT_COLUMN,ingredient);
        contentValues.put(QUANTITY_COLUMN,quantity);
        //if units equals "none" then an empty string is added
        if(units.equals("none")){
            contentValues.put(UNITS_COLUMN, "");
        }
        else
            contentValues.put(UNITS_COLUMN,units);
        return -1 != db.insert(TABLE_NAME, null, contentValues);
    }

    //called whenever the app is upgraded and launched and the database version is not the same.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //returns an object of Type Cursor which contains all the data from the "kitchen" table
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(query, null);
        return res;
    }

    //returns true
    //replaces the quantity and units in the row of the given ingredient
    public boolean updateData(String ingredient,Double quantity,String units) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(INGREDIENT_COLUMN,ingredient);
        contentValues.put(QUANTITY_COLUMN,quantity);
        //if units equals "none" then an empty string is added
        if(units.equals("none")){
            contentValues.put(UNITS_COLUMN, "");
        }
        else
            contentValues.put(UNITS_COLUMN,units);
        db.update(TABLE_NAME, contentValues, INGREDIENT_COLUMN + " = ?" ,new String[] { ingredient });
        return true;
    }

    //deletes rows with the ingredient specified and returns the number of deleted rows
    public Integer deleteIngredient (String ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                INGREDIENT_COLUMN + " = ?",//where clause (what is the ingredient? --> "INGREDIENT = ?"
                new String[] {ingredient});//value
    }

    //accessor method for databse
    public SQLiteDatabase getDatabase(){
        return this.getWritableDatabase();
    }
}
