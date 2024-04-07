package com.devpro.android54_day7;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.devpro.android54_day7.Interface.IOnClickQuantityModel;
import com.devpro.android54_day7.Model.CartItem;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cart.db";
    public static final String FOOD_TABLE = "FOOD";

    public static final String ID_COLUMN = "id";
    public static final String FOOD_NAME_COLUMN = "food_name";
    public static final String IMAGE_COLUMN = "image";
    public static final String PRICE_COLUMN = "price";
    public static final String QUANTITY_COLUMN = "quantity";
    private static final ArrayList<CartItem> mlist = new ArrayList<>();
    private Context context;
    private static final int VERSION = 1;
    private IOnClickQuantityModel iOnClickQuantityModel;

    public void setiOnClickQuantityModel(IOnClickQuantityModel iOnClickQuantityModel) {
        this.iOnClickQuantityModel = iOnClickQuantityModel;
    }

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + FOOD_TABLE + "("
                + ID_COLUMN + " TEXT NOT NULL PRIMARY KEY ,"
                + FOOD_NAME_COLUMN + " TEXT NOT NULL, "
                + PRICE_COLUMN + " NUMERIC NOT NULL, "
                + QUANTITY_COLUMN + " NUMERIC NOT NULL, "
                + IMAGE_COLUMN + " NUMERIC NOT NULL);";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){

    }
    public void addFood(CartItem cartItem) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID_COLUMN, cartItem.getId());
        contentValues.put(FOOD_NAME_COLUMN, cartItem.getFoodName());
        contentValues.put(PRICE_COLUMN, cartItem.getPrice());
        contentValues.put(IMAGE_COLUMN, cartItem.getImageFood());
        contentValues.put(QUANTITY_COLUMN, cartItem.getQuantity());

        database.insert(FOOD_TABLE, null, contentValues);
        database.close();
    }

    public ArrayList<CartItem> getAll() {
        SQLiteDatabase database = getReadableDatabase();
        mlist.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM " + FOOD_TABLE, null);

        while (cursor.moveToNext()) {
            CartItem item = getItemFromCursor(cursor);
            mlist.add(item);
        }
        cursor.close();
        database.close();
        return mlist;
    }

    private CartItem getItemFromCursor(Cursor cursor) {

        @SuppressLint("Range")
        int id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
        @SuppressLint("Range")
        Integer image = cursor.getInt(cursor.getColumnIndex(IMAGE_COLUMN));
        @SuppressLint("Range")
        String foodname = cursor.getString(cursor.getColumnIndex(FOOD_NAME_COLUMN));
        @SuppressLint("Range")
        double price = cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN));

        return new CartItem(id, image, foodname, price);
    }

    @SuppressLint("Range")
    public void onClickPlus(int idItem) {
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + FOOD_TABLE + " WHERE " + ID_COLUMN + "=?",
                new String[]{String.valueOf(idItem)});
        if (cursor.moveToFirst()) {
            @SuppressLint("Range")
            int id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
            @SuppressLint("Range")
            Integer image = cursor.getInt(cursor.getColumnIndex(IMAGE_COLUMN));
            @SuppressLint("Range")
            String foodname = cursor.getString(cursor.getColumnIndex(FOOD_NAME_COLUMN));
            @SuppressLint("Range")
            double price = cursor.getDouble(cursor.getColumnIndex(PRICE_COLUMN));
            @SuppressLint("Range")
            double quantity = cursor.getDouble(cursor.getColumnIndex(QUANTITY_COLUMN));

            double newQuantity = quantity * 1;

            ContentValues values = new ContentValues();
            values.put(PRICE_COLUMN, newQuantity);
            String whereClause = ID_COLUMN + " =?";
            String[] whereArgs = {String.valueOf(idItem)};
            database.update(FOOD_TABLE, values, whereClause, whereArgs);


            cursor.close();
            database.close();
        }

    }
}

