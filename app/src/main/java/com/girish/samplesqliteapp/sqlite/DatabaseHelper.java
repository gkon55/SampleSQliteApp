package com.girish.samplesqliteapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.girish.samplesqliteapp.model.Chef;
import com.girish.samplesqliteapp.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by girish on 1/12/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restaurants_db";

   // Table Names
    private static final String TABLE_RESTAURANT = "restaurant";
    private static final String TABLE_CHEF = "chef";
    private static final String TABLE_WORK = "work";

    //Restaurant table column names
    private static final String RESTAURANT_ID = "id";
    private static final String RESTAURANT_NAME = "Name";
    private static final String RESTAURANT_ADD1 = "address1";
    private static final String RESTAURANT_ADD2 = "address2";
    private static final String RESTAURANT_CITY = "city";
    private static final String RESTAURANT_STATE = "state";
    private static final String RESTAURANT_COUNTRY = "country";
    private static final String RESTAURANT_PHONE = "phone";

    //Chef table column names
    private static final String CHEF_ID = "Id";
    private static final String CHEF_FIRSTNAME = "First_name";
    private static final String CHEF_SECONDNAME = "Second_name";
    private static final String CHEF_ADD = "Address";
    private static final String CHEF_CITY = "City";
    private static final String CHEF_STATE= "State";
    private static final String CHEF_COUNTRY = "Country";
    private static final String CHEF_EMAIL= "email";

    //Work table columns names
    private static final String WORK_REST_ID = "Restaurant_id";
    private static final String WORK_CHEF_ID = "Chef_id";

    //Create Restaurant Table
    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE " + TABLE_RESTAURANT
            + "(" + RESTAURANT_ID + " INTEGER PRIMARY KEY," + RESTAURANT_NAME
            + " TEXT," + RESTAURANT_ADD1 + " TEXT," + RESTAURANT_ADD2  + " TEXT," + RESTAURANT_CITY
            + " TEXT," + RESTAURANT_STATE  + " TEXT," + RESTAURANT_COUNTRY  + " TEXT," + RESTAURANT_PHONE + " TEXT" +")";


    //Create Chef Table
    private static final String CREATE_TABLE_CHEF = "CREATE TABLE " +  TABLE_CHEF
            + "(" + CHEF_ID + " INTEGER PRIMARY KEY," + CHEF_FIRSTNAME  + " TEXT," + CHEF_SECONDNAME
            + " TEXT," + CHEF_ADD  + " TEXT," + CHEF_CITY  + " TEXT," + CHEF_STATE  + " TEXT," + CHEF_COUNTRY
            + " TEXT," + CHEF_EMAIL  + " TEXT" + ")";

    //Create Work Table
    private static final String CREATE_TABLE_WORK = "CREATE TABLE "
            + TABLE_WORK + "(" + WORK_REST_ID + " INTEGRE," + WORK_CHEF_ID + " INTEGER" + ")";




    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_RESTAURANT);
            db.execSQL(CREATE_TABLE_CHEF);

            db.execSQL(CREATE_TABLE_WORK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHEF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORK);

        onCreate(db);
    }

    //Create Restaurants
    public void createRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID, restaurant.getId());
        values.put(RESTAURANT_NAME, restaurant.getName());
        values.put(RESTAURANT_ADD1, restaurant.getAddress1());
        values.put(RESTAURANT_ADD2, restaurant.getAddress2());
        values.put(RESTAURANT_CITY, restaurant.getCity());
        values.put(RESTAURANT_STATE, restaurant.getState());
        values.put(RESTAURANT_COUNTRY, restaurant.getCountry());
        values.put(RESTAURANT_PHONE, restaurant.getPhone());

        // insert row
            db.insert(TABLE_RESTAURANT, null, values);
            db.close();

    }

    //Create Chef
    public void createChef(Chef chef) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CHEF_ID, chef.getId());
        values.put(CHEF_FIRSTNAME, chef.getFirstName());
        values.put(CHEF_SECONDNAME, chef.getSecondName());
        values.put(CHEF_ADD, chef.getAddress());
        values.put(CHEF_CITY, chef.getCity());
        values.put(CHEF_STATE, chef.getState());
        values.put(CHEF_COUNTRY, chef.getCountry());
        values.put(CHEF_EMAIL, chef.getEmail());

        // insert row
        db.insert(TABLE_CHEF, null, values);
        db.close();

    }

    public void createWork(int rest_id, int chef_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WORK_REST_ID, rest_id);
        values.put(WORK_CHEF_ID, chef_id);

        long id = db.insert(TABLE_WORK, null, values);
        db.close();
       // return id;
    }

    //get ALl restaurant list
    public ArrayList<Restaurant> getAllRestaurantList(){

        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        String selectQueryRestaurant = "SELECT * FROM " + TABLE_RESTAURANT;
        Log.d("DatabaseHelper", selectQueryRestaurant);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQueryRestaurant, null);
        if (cursor.moveToFirst()) {
            do {
                Restaurant restObj = new Restaurant();
                restObj.setId(cursor.getInt((cursor.getColumnIndex(RESTAURANT_ID))));
                restObj.setName((cursor.getString(cursor.getColumnIndex(RESTAURANT_NAME))));
                restObj.setAddress1(cursor.getString(cursor.getColumnIndex(RESTAURANT_ADD1)));
                restObj.setAddress2(cursor.getString(cursor.getColumnIndex(RESTAURANT_ADD2)));
                restObj.setCity(cursor.getString(cursor.getColumnIndex(RESTAURANT_CITY)));
                restObj.setState(cursor.getString(cursor.getColumnIndex(RESTAURANT_STATE)));
                restObj.setCountry(cursor.getString(cursor.getColumnIndex(RESTAURANT_COUNTRY)));
                restObj.setPhone(cursor.getString(cursor.getColumnIndex(RESTAURANT_PHONE)));

                restaurantList.add(restObj);
            } while (cursor.moveToNext());
        }

        return restaurantList;
    }

    public List<Chef> getAllChefsList(){

        List<Chef> chefList = new ArrayList<Chef>();
        String selectQueryChef = "SELECT * FROM " + TABLE_CHEF;
        Log.d("DatabaseHelper", selectQueryChef);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQueryChef, null);
        if (cursor.moveToFirst()) {
            do {
                Chef chefObj = new Chef();
                chefObj.setId(cursor.getInt((cursor.getColumnIndex(CHEF_ID))));
                chefObj.setFirstName((cursor.getString(cursor.getColumnIndex(CHEF_FIRSTNAME))));
                chefObj.setSecondName(cursor.getString(cursor.getColumnIndex(CHEF_SECONDNAME)));
                chefObj.setAddress(cursor.getString(cursor.getColumnIndex(CHEF_ADD)));
                chefObj.setCity(cursor.getString(cursor.getColumnIndex(CHEF_CITY)));
                chefObj.setState(cursor.getString(cursor.getColumnIndex(CHEF_STATE)));
                chefObj.setCountry(cursor.getString(cursor.getColumnIndex(CHEF_COUNTRY)));
                chefObj.setEmail(cursor.getString(cursor.getColumnIndex(CHEF_EMAIL)));

                chefList.add(chefObj);
            } while (cursor.moveToNext());
        }

        return chefList;
    }

    public List<Chef> getAllChefsByRestaurant(int restID) {
        List<Chef> chefsList = new ArrayList<Chef>();

        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT + " tr, "
                + TABLE_CHEF + " tc, " + TABLE_WORK + " tw WHERE tr."
                + RESTAURANT_ID + " = '" + restID + "'" + " AND tr." + RESTAURANT_ID
                + " = " + "tw." + WORK_REST_ID + " AND tc." + CHEF_ID+ " = "
                + "tw." + WORK_CHEF_ID;

        Log.e("getAllChefsByRestr", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Chef chefObj1 = new Chef();
                chefObj1.setId(c.getInt((c.getColumnIndex(CHEF_ID))));
                chefObj1.setFirstName((c.getString(c.getColumnIndex(CHEF_FIRSTNAME))));
                chefObj1.setSecondName(c.getString(c.getColumnIndex(CHEF_SECONDNAME)));
                chefObj1.setAddress(c.getString(c.getColumnIndex(CHEF_ADD)));
                chefObj1.setCity(c.getString(c.getColumnIndex(CHEF_CITY)));
                chefObj1.setState(c.getString(c.getColumnIndex(CHEF_STATE)));
                chefObj1.setCountry(c.getString(c.getColumnIndex(CHEF_COUNTRY)));
                chefObj1.setEmail(c.getString(c.getColumnIndex(CHEF_EMAIL)));
                // adding to chef list
                chefsList.add(chefObj1);
            } while (c.moveToNext());
        }

        return chefsList;
    }
    public boolean isTableExists() {
        SQLiteDatabase mDatabase = this.getReadableDatabase();

        Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                + TABLE_WORK + "'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                mDatabase.execSQL("delete from "+ TABLE_WORK);
                return false;
            }
            cursor.close();
        }
        return true;
    }
}

