package com.example.halalzone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ADMIN " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS USER " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, phone TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS BUSINESSES " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, phone TEXT NOT NULL, status TEXT NOT NULL, business_email TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORY " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS ITEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, category_id TEXT NOT NULL, name TEXT NOT NULL, price REAL NOT NULL, dis_price REAL, description TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS CART_ITEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, item_id INTEGER NOT NULL, username TEXT NOT NULL, " +
                "FOREIGN KEY (item_id) REFERENCES ITEM(id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS ORDER_TABLE " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, datetime TEXT NOT NULL, total REAL NOT NULL, email TEXT NOT NULL)" );

        db.execSQL("CREATE TABLE IF NOT EXISTS ORDER_ITEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, order_id INTEGER NOT NULL, item_id INTEGER NOT NULL, quantity INTEGER NOT NULL, price REAL NOT NULL, total REAL NOT NULL, " +
                "FOREIGN KEY (order_id) REFERENCES ORDER_TABLE(id), FOREIGN KEY (item_id) REFERENCES ITEM(id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS PROBLEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, order_id INTEGER NOT NULL, order_item_id INTEGER NOT NULL, problem_description TEXT NOT NULL, date TEXT NOT NULL, status TEXT NOT NULL, note TEXT, " +
                "FOREIGN KEY (order_id) REFERENCES ORDER_TABLE(id), FOREIGN KEY (order_item_id) REFERENCES ORDER_ITEM(id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS BUSINESSES_REVIEWS " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, business_email TEXT NOT NULL, user_email TEXT NOT NULL, rate INTEGER NOT NULL CHECK (rate BETWEEN 1 AND 5), note TEXT, date TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS BUSINESSES_WARNING " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, business_email TEXT NOT NULL, note TEXT NOT NULL, date TEXT NOT NULL)");
    }

    public Boolean addAdmin(String Username,String Name,String Password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",Username);
        contentValues.put("name",Name);
        contentValues.put("password",Password);
        long result = MyDB.insert("ADMIN",null,contentValues);
        if (result == -1 ) {
            return false;
        }   else{
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS ADMIN");
//        db.execSQL("DROP TABLE IF EXISTS USER");
//        db.execSQL("DROP TABLE IF EXISTS BUSINESSES");
//        db.execSQL("DROP TABLE IF EXISTS CATEGORY");
//        db.execSQL("DROP TABLE IF EXISTS ITEM");
//        db.execSQL("DROP TABLE IF EXISTS CART_ITEM");
//        db.execSQL("DROP TABLE IF EXISTS ORDER_TABLE");
//        db.execSQL("DROP TABLE IF EXISTS ORDER_ITEM");
//        db.execSQL("DROP TABLE IF EXISTS PROBLEM");
//        db.execSQL("DROP TABLE IF EXISTS BUSINESSES_REVIEWS");
//        db.execSQL("DROP TABLE IF EXISTS BUSINESSES_WARNING");
//        onCreate(db);
//        try {
//            db.execSQL("ALTER TABLE ITEM ADD COLUMN business_email TEXT NOT NULL DEFAULT ''");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        db.execSQL("DROP TABLE IF EXISTS ITEM");
//        db.execSQL("CREATE TABLE IF NOT EXISTS ITEM " +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT, category_id TEXT NOT NULL, " +
//                "name TEXT NOT NULL, price REAL NOT NULL, dis_price REAL, description TEXT)");

    }

    public Boolean checkadmin (String email, String Password){
        addAdmin("admin","admin","admin");
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from ADMIN where email = ? and password = ?",new String[]{email,Password});
        if (cursor.getCount() > 0 ) {
            return true;
        }   else{
            return false;
        }
    }

    public Boolean checkbusiness (String email, String Password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from BUSINESSES where email = ? and password = ?",new String[]{email,Password});
        if (cursor.getCount() > 0 ) {
            return true;
        }   else{
            return false;
        }
    }


    public Boolean adduser(String email,String Name,String Password,String Phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",Name);
        contentValues.put("password",Password);
        contentValues.put("phone",Phone);
        long result = MyDB.insert("USER",null,contentValues);
        if (result == -1 ) {
            return false;
        }   else{
            return true;
        }
    }

    public Boolean addbusiness(String email,String Name,String Password,String Phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",Name);
        contentValues.put("password",Password);
        contentValues.put("phone",Phone);
        contentValues.put("status","Done");
        long result = MyDB.insert("BUSINESSES",null,contentValues);
        if (result == -1 ) {
            return false;
        }   else{
            return true;
        }
    }

    public Boolean addproduct(String category_id,String Name,String business_email,double price,double dis_price,String description){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_id",category_id);
        contentValues.put("Name",Name);
        contentValues.put("business_email",business_email);
        contentValues.put("price",price);
        contentValues.put("dis_price",dis_price);
        contentValues.put("description",description);
        long result = MyDB.insert("ITEM",null,contentValues);
        if (result == -1 ) {
            return false;
        }   else{
            return true;
        }
    }


    public Boolean checkuser (String email, String Password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from USER where email = ? and password = ?",new String[]{email,Password});
        if (cursor.getCount() > 0 ) {
            return true;
        }   else{
            return false;
        }
    }
    public String getname (String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select name from USER where email = ?",new String[]{email});
        if (cursor.moveToFirst()) { // Move the cursor to the first row
            String name = cursor.getString(0); // Index should be 0 if 'name' is the only column retrieved
            cursor.close(); // Always close the cursor after you're done
            return name;
        } else {
            cursor.close(); // Always close the cursor even if there's no data found
            return "";
        }
    }


    public Cursor getProblems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                "PROBLEM",        // The table to query
                new String[] {"id as _id","order_id" , "order_item_id" , "problem_description", "date", "status" , "note"}, // The columns to return
                null,     // The columns for the WHERE clause
                null,     // The values for the WHERE clause
                null,           // group the rows
                null,           // filter by row groups
                null            // The sort order
        );
    }


    public Cursor getBusiness() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                "BUSINESSES",        // The table to query
                new String[] {"id as _id","email" , "password" , "name", "phone", "status"}, // The columns to return
                null,     // The columns for the WHERE clause
                null,     // The values for the WHERE clause
                null,           // group the rows
                null,           // filter by row groups
                null            // The sort order
        );
    }


}
