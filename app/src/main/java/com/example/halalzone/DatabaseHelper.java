package com.example.halalzone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class DatabaseHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 16;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ADMIN " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS USER " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, phone TEXT, gender TEXT, address TEXT, country TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS BUSINESSES " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT UNIQUE NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, phone TEXT NOT NULL, status TEXT NOT NULL,  image BLOB, type TEXT, country TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORY " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS ITEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, category_id TEXT NOT NULL, name TEXT NOT NULL, price REAL NOT NULL, dis_price REAL, description TEXT, image BLOB, business_email TEXT)");

        db.execSQL("CREATE TABLE IF NOT EXISTS CART_ITEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, item_id INTEGER NOT NULL, username TEXT NOT NULL, QTY INTEGER, " +
                "FOREIGN KEY (item_id) REFERENCES ITEM(id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS ORDER_TABLE " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, datetime TEXT NOT NULL, total REAL NOT NULL, email TEXT NOT NULL, paymentMethod TEXT)" );

        db.execSQL("CREATE TABLE IF NOT EXISTS ORDER_ITEM " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, order_id INTEGER NOT NULL, item_id INTEGER NOT NULL, quantity INTEGER NOT NULL, price REAL NOT NULL, total REAL NOT NULL, " +
                "FOREIGN KEY (order_id) REFERENCES ORDER_TABLE(id), FOREIGN KEY (item_id) REFERENCES ITEM(id))");

        db.execSQL("CREATE TABLE IF NOT EXISTS BUSINESSES_REVIEWS " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, business_email TEXT NOT NULL, user_email TEXT NOT NULL, rate INTEGER NOT NULL CHECK (rate BETWEEN 1 AND 5), note TEXT, date TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS BUSINESSES_WARNING " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, business_email TEXT NOT NULL, note TEXT NOT NULL, date TEXT NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS VIDEOS " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, VIDEO_ID TEXT NOT NULL,NUM INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS PROBLEM (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Using _id as the primary key
                "order_id TEXT, " +
                "order_item_id TEXT, " +
                "problem_description TEXT, " +
                "date TEXT, " +
                "status TEXT, " +
                "note TEXT);");

        db.execSQL("CREATE TABLE IF NOT EXISTS HALAL_ITEMS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Using _id as the primary key
                "barcode TEXT UNIQUE);");
    }

    public String getMaxNumVideoId() {
        String videoId = null;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT VIDEO_ID FROM VIDEOS ORDER BY NUM DESC LIMIT 1";

        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            videoId = cursor.getString(0);
        }
        cursor.close();
        return videoId;
    }
    public int getNextNum() {
        SQLiteDatabase db = this.getReadableDatabase();
        int nextNum = 1; // Default to 1 if no rows exist

        String query = "SELECT COUNT(*) FROM VIDEOS";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            nextNum = cursor.getInt(0) + 1; // Get count and add 1
        }
        cursor.close();
        return nextNum;
    }


    public void addVideo(String videoId) {
        SQLiteDatabase db = this.getWritableDatabase();

        int num = getNextNum(); // Get the next NUM value

        ContentValues values = new ContentValues();
        values.put("VIDEO_ID", videoId);
        values.put("NUM", num);

        db.insert("VIDEOS", null, values);
        db.close();
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
//        db.execSQL("DROP TABLE IF EXISTS HALAL_ITEMS");
        onCreate(db);
        // OxB2h_1ZS74
//        try {
//            db.execSQL("ALTER TABLE ITEM ADD COLUMN business_email TEXT NOT NULL DEFAULT ''");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        db.execSQL("DROP TABLE IF EXISTS ITEM");
//        db.execSQL("CREATE TABLE IF NOT EXISTS ITEM " +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT, category_id TEXT NOT NULL, " +
//                "name TEXT NOT NULL, price REAL NOT NULL, dis_price REAL, description TEXT)");
//        if (oldVersion < 5) { // Increment database version when adding the column
//            try {
//                db.execSQL("ALTER TABLE ITEM ADD COLUMN image BLOB");
//                db.execSQL("ALTER TABLE BUSINESSES ADD COLUMN image BLOB");
//                db.execSQL("ALTER TABLE BUSINESSES ADD COLUMN type TEXT");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

//        if (oldVersion < 13) { // Increment database version when adding the column
//            try {
//                db.execSQL("ALTER TABLE BUSINESSES ADD COLUMN country TEXT");
//                db.execSQL("ALTER TABLE USER ADD COLUMN country TEXT");
//                db.execSQL("ALTER TABLE USER ADD COLUMN address TEXT");
//                db.execSQL("ALTER TABLE USER ADD COLUMN gender TEXT");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (oldVersion < 8) { // Increment database version when adding the column
//            try {
//                db.execSQL("ALTER TABLE CART_ITEM ADD COLUMN QTY INTEGER");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (oldVersion < 10) {
//            try {
//            db.execSQL("CREATE TABLE IF NOT EXISTS VIDEOS " +
//                "(id INTEGER PRIMARY KEY AUTOINCREMENT, VIDEO_ID TEXT NOT NULL)");
//                db.execSQL("ALTER TABLE VIDEOS ADD COLUMN NUM INTEGER");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public boolean insertBarcode(String barcode) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("barcode", barcode);

        long result = db.insert("HALAL_ITEMS", null, values);
        return result != -1; // Return true if inserted successfully
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

    public Boolean adduser(String email,String Name,String Password,String Phone,String Address,String Country,String Gender){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",Name);
        contentValues.put("password",Password);
        contentValues.put("phone",Phone);
        contentValues.put("address",Address);
        contentValues.put("gender",Gender);
        contentValues.put("country",Country);
        long result = MyDB.insert("USER",null,contentValues);
        if (result == -1 ) {
            return false;
        }   else{
            return true;
        }
    }

    public Boolean addbusiness(String email,String Name,String Password,String Phone, String type, String country){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("name",Name);
        contentValues.put("password",Password);
        contentValues.put("phone",Phone);
        contentValues.put("type",type);
        contentValues.put("country",country);
        contentValues.put("status","Done");
        long result = MyDB.insert("BUSINESSES",null,contentValues);
        if (result == -1 ) {
            return false;
        }   else{
            return true;
        }
    }

    public Boolean addproduct(String category_id,String Name,String business_email,double price,double dis_price,String description, byte[] image){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_id",category_id);
        contentValues.put("Name",Name);
        contentValues.put("business_email",business_email);
        contentValues.put("price",price);
        contentValues.put("dis_price",dis_price);
        contentValues.put("description",description);
        contentValues.put("image", image); // Store image as BLOB
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

    public List<Product> getProductsByEmail(String email) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, category_id, name, price, dis_price, description, image, business_email FROM ITEM WHERE business_email=?", new String[]{email});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String category = cursor.getString(1);
                String name = cursor.getString(2);
                double price = cursor.getDouble(3);
                double discountPrice = cursor.getDouble(4);
                String description = cursor.getString(5);
                byte[] image = cursor.getBlob(6);

                Product product = new Product(id, category, name, email, price, discountPrice, description, image);
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return productList;
    }


    public boolean updateDiscountPrice(int productId, double newDiscountPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("dis_price", newDiscountPrice);

        int rowsAffected = db.update("ITEM", values, "id=?", new String[]{String.valueOf(productId)});
        db.close();
        return rowsAffected > 0;
    }


    // Method to insert a new warning
    public boolean addWarning(String businessEmail, String note, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("business_email", businessEmail);
        values.put("note", note);
        values.put("date", date);

        long result = db.insert("BUSINESSES_WARNING", null, values);
        return result != -1;
    }

    // Method to retrieve all warnings
    public List<Warning> getAllWarnings() {
        List<Warning> warningsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BUSINESSES_WARNING", null);

        if (cursor.moveToFirst()) {
            do {
                Warning warning = new Warning(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                warningsList.add(warning);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return warningsList;
    }


    public List<Warning> getWarningsByEmail(String businessEmail) {
        List<Warning> warningsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM BUSINESSES_WARNING WHERE business_email=?", new String[]{businessEmail});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                Warning warning = new Warning(id, businessEmail, note, date);
                warningsList.add(warning);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return warningsList;
    }

    public boolean deleteProduct(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("ITEM", "id=?", new String[]{String.valueOf(productId)});
        db.close();
        return rowsAffected > 0;
    }


    public byte[] getImage(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("BUSINESSES", new String[]{"image"}, "email" + "=?",
                new String[]{String.valueOf(email)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") byte[] imageBytes = cursor.getBlob(cursor.getColumnIndex("image"));
            cursor.close();
            return imageBytes;  // Return the image as byte array if it exists
        }

        cursor.close();
        return null;  // Return null if no image is found
    }



    public boolean updateBusinessImage(String email, Bitmap image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // Convert the Bitmap to a byte array
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        contentValues.put("image", imageBytes);

        // Update the image in the database for the specified business ID
        int rowsAffected = db.update("BUSINESSES", contentValues, "email =?", new String[]{String.valueOf(email)});
        return rowsAffected > 0;  // Return true if the update was successful
    }


    public ArrayList<OfferModel> getOfferItems(String username) {
        ArrayList<OfferModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT id, name, business_email, price, dis_price, image FROM ITEM WHERE dis_price != 0 AND ", null);
        Cursor cursor = db.rawQuery(
                "SELECT ITEM.id, ITEM.name, ITEM.business_email, ITEM.price, ITEM.dis_price, ITEM.image, BUSINESSES.country " +
                        "FROM ITEM " +
                        "JOIN BUSINESSES ON ITEM.business_email = BUSINESSES.business_email " +
                        "WHERE ITEM.dis_price != 0 " +
                        "AND BUSINESSES.country = (SELECT u.country FROM USER u WHERE UPPER(u.email) = ?)",
                new String[]{username.toUpperCase()}
        );



        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int itemId = cursor.getInt(0);
                        String name = cursor.getString(1);
                        String business = cursor.getString(2);
                        String oldPrice = String.valueOf(cursor.getDouble(3));
                        String newPrice = String.valueOf(cursor.getDouble(4));
                        byte[] imageBytes = cursor.getBlob(5);

                        // Check if imageBytes is null or empty
                        if (imageBytes == null || imageBytes.length == 0) {
                            Log.d("OfferActivity", "Image bytes are null or empty for item: " + name);
                        } else {
                            Log.d("OfferActivity", "Image bytes length: " + imageBytes.length + " for item: " + name);
                        }

                        // Decode the image if it's not null
                        Bitmap image = null;
                        if (imageBytes != null && imageBytes.length > 0) {
                            image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                        }

                        list.add(new OfferModel(itemId, name, business, oldPrice, newPrice, image));
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();  // Log any exceptions
            } finally {
                cursor.close();
            }
        }
        db.close();
        return list;
    }

    public ItemmModel getItemById(int itemId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id, name, category_id, business_email, price, dis_price, image, description FROM ITEM WHERE id = ?",
                new String[]{String.valueOf(itemId)});

        ItemmModel item = null;

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    String name = cursor.getString(1);
                    int category_id = cursor.getInt(2);
                    String business = cursor.getString(3);
                    String oldPrice = String.valueOf(cursor.getDouble(4));
                    String newPrice = String.valueOf(cursor.getDouble(5));
                    byte[] imageBytes = cursor.getBlob(6);
                    String description = String.valueOf(cursor.getDouble(7));

                    Bitmap image = null;
                    if (imageBytes != null && imageBytes.length > 0) {
                        image = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    }

                    item = new ItemmModel(itemId, name, business,description, oldPrice, newPrice, image);
                }
            } catch (Exception e) {
                e.printStackTrace();  // Log any exceptions
            } finally {
                cursor.close();
            }
        }
        db.close();
        return item;
    }

    public boolean addToCart(int item_id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the item already exists in the cart for the given username
        Cursor cursor = db.rawQuery("SELECT id, QTY FROM CART_ITEM WHERE item_id = ? AND username = ?",
                new String[]{String.valueOf(item_id), username});

        boolean result = false;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                // If the item exists, update the quantity by adding 1
                @SuppressLint("Range") int currentQty = cursor.getInt(cursor.getColumnIndex("QTY"));
                ContentValues contentValues = new ContentValues();
                contentValues.put("QTY", currentQty + 1);

                int rowsUpdated = db.update("CART_ITEM", contentValues, "item_id = ? AND username = ?",
                        new String[]{String.valueOf(item_id), username});
                result = rowsUpdated > 0; // If rowsUpdated > 0, the update was successful
            } else {
                // If the item doesn't exist, insert a new row into the cart
                ContentValues contentValues = new ContentValues();
                contentValues.put("item_id", item_id);
                contentValues.put("username", username);
                contentValues.put("QTY", 1);

                long insertResult = db.insert("CART_ITEM", null, contentValues);
                result = insertResult != -1; // If insertResult != -1, the insert was successful
            }
            cursor.close();
        }

        db.close();
        return result;
    }

    public ArrayList<CartItem> getCartItems(String username) {
        ArrayList<CartItem> list = new ArrayList<>();

        // Check if username is null or empty
        if (username == null || username.isEmpty()) {
            Log.e("DatabaseHelper", "Username is null or empty");
            return list;  // Return empty list or handle the error appropriately
        }

        SQLiteDatabase db = this.getReadableDatabase();

        // Modified query to join CART_ITEM with ITEM table to fetch name, price, and image
        String query = "SELECT ci.item_id, i.name, CASE WHEN i.dis_price > 0 THEN i.dis_price ELSE i.price END price, ci.qty, i.image " +
                "FROM CART_ITEM ci " +
                "JOIN ITEM i ON ci.item_id = i.id " + // Here we join on 'id' from ITEM table
                "WHERE ci.username = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username});

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int itemId = cursor.getInt(0);
                        String name = cursor.getString(1);
                        double price = cursor.getDouble(2);
                        int qty = cursor.getInt(3);
                        byte[] imageBytes = cursor.getBlob(4); // Get the image as byte array

                        // You can convert byte[] to Bitmap if needed for ImageView
                        Bitmap image = imageBytes != null ? BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length) : null;

                        // Add the cart item to the list
                        list.add(new CartItem(itemId, name, price, qty, image));  // Updated constructor to include image
                    } while (cursor.moveToNext());
                }
            } catch (Exception e) {
                e.printStackTrace();  // Log any exceptions
            } finally {
                cursor.close();
            }
        }

        db.close();
        return list;
    }

    public void removeCartItem(int itemId, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CART_ITEM", "item_id = ? AND username = ?", new String[]{String.valueOf(itemId), username});
        db.close();
    }

    public double getTotalPriceForUserCart(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to fetch total price by checking if discount_price is greater than 0
        String query = "SELECT SUM(CASE WHEN i.dis_price > 0 THEN i.dis_price ELSE i.price END * ci.qty) AS total_price " +
                "FROM CART_ITEM ci " +
                "JOIN ITEM i ON ci.item_id = i.id " +
                "WHERE ci.username = ?";

        Cursor cursor = db.rawQuery(query, new String[]{username});
        double totalPrice = 0;

        if (cursor.moveToFirst()) {
            totalPrice = cursor.getDouble(0);
            cursor.close();
        }

        db.close();
        return totalPrice;
    }

    public void updateCartItemQuantity(int itemId, int quantity, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Prepare the values to update
        ContentValues contentValues = new ContentValues();
        contentValues.put("qty", quantity);

        // Update the cart item in the database where item_id and username match
        String whereClause = "item_id = ? AND  username = ?";
        String[] whereArgs = {String.valueOf(itemId), username};

        db.update("CART_ITEM", contentValues, whereClause, whereArgs);
        db.close();
    }

    public long placeOrder(String email, double total, List<CartItem> cartItems, String paymentMethod) {
        SQLiteDatabase db = this.getWritableDatabase();
        long orderId = -1;

        try {
            db.beginTransaction();

            // Insert into ORDER_TABLE
            ContentValues orderValues = new ContentValues();
            orderValues.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
            orderValues.put("total", total);
            orderValues.put("email", email);
            orderValues.put("paymentMethod", paymentMethod);

            orderId = db.insert("ORDER_TABLE", null, orderValues);

            if (orderId == -1) {
                throw new Exception("Failed to insert order.");
            }

            // Insert order items
            for (CartItem item : cartItems) {
                ContentValues itemValues = new ContentValues();
                itemValues.put("order_id", orderId);
                itemValues.put("item_id", item.getItemId());
                itemValues.put("quantity", item.getQty());
                itemValues.put("price", item.getPrice());
                itemValues.put("total", item.getPrice() * item.getQty());

                long itemInserted = db.insert("ORDER_ITEM", null, itemValues);
                if (itemInserted == -1) {
                    throw new Exception("Failed to insert order item.");
                }
            }

            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            orderId = -1;
        } finally {
            db.endTransaction();
            db.close();
        }

        return orderId;  // Return order ID if successful, -1 otherwise
    }


    public void clearCart(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("CART_ITEM", "username = ?", new String[]{username});
        db.close();
    }

    public ArrayList<Business> getBusinesses(String type1,String username) {
        ArrayList<Business> restaurantsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM BUSINESSES WHERE status != ? AND type = ? AND country = (SELECT u.country FROM USER u WHERE UPPER(u.email) = ?)", new String[]{"blocked", type1, username});

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));

                // Create Business object and add to list
                restaurantsList.add(new Business(id, email, password, name, phone, status, image, type));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return restaurantsList;
    }


    public ArrayList<itemModel> getItemsByBusinessmail(String businessEmail) {
        ArrayList<itemModel> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            // Null check for businessEmail before calling toUpperCase()
            if (businessEmail != null) {
                cursor = db.rawQuery("SELECT id, name, price, dis_price, image FROM ITEM WHERE upper(business_email) = ?",
                        new String[]{businessEmail.toUpperCase()});
            } else {
                // Handle the case where the email is null (perhaps log an error or return an empty list)
                return itemList;
            }

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    double price = cursor.getDouble(2);
                    double disPrice = cursor.isNull(3) ? price : cursor.getDouble(3);
                    byte[] image = cursor.getBlob(4);

                    itemList.add(new itemModel(id, name, String.valueOf(price) + " USD", String.valueOf(disPrice) + " USD", image));
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            // Close cursor and database in the finally block to ensure they are always closed
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return itemList;
    }


    public List<OrderHistory> fetchOrderHistory(String email) {
        List<OrderHistory> orderHistoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Query to get order id, datetime, and total for the given email
        Cursor cursor = db.rawQuery(
                "SELECT o.id, o.datetime, o.total ,o.paymentMethod FROM ORDER_TABLE o " +
                        "INNER JOIN ORDER_ITEM oi ON o.id = oi.order_id " +
                        "WHERE o.email = ? GROUP BY o.id",
                new String[]{email});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve order id, datetime, and total
                int orderId = cursor.getInt(0);
                String orderDate = cursor.getString(1);
                double totalAmount = cursor.getDouble(2);
                String paymentMethod = cursor.getString(3);

                // Add order to the list
                orderHistoryList.add(new OrderHistory(orderId, orderDate, totalAmount,paymentMethod));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return orderHistoryList;
    }

    public List<OrderItem> fetchOrderItems(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT oi.item_id, oi.quantity, oi.price, oi.total " +
                        "FROM ORDER_ITEM oi " +
                        "WHERE oi.order_id = ?",
                new String[]{String.valueOf(orderId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int itemId = cursor.getInt(0);
                int quantity = cursor.getInt(1);
                double price = cursor.getDouble(2);
                double total = cursor.getDouble(3);

                orderItemList.add(new OrderItem(itemId, quantity, price, total));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return orderItemList;
    }

    public boolean insertProblem(int orderId, int orderItemId, String problemDescription, String date, String status, String note) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Use a ContentValues object to store the data to be inserted
        ContentValues contentValues = new ContentValues();
        contentValues.put("order_id", orderId);
        contentValues.put("order_item_id", orderItemId);
        contentValues.put("problem_description", problemDescription);
        contentValues.put("date", date);
        contentValues.put("status", status);
        contentValues.put("note", note);

        // Insert the data into the PROBLEM table
        long result = db.insert("PROBLEM", null, contentValues);

        db.close();

        // If the result is -1, the insert failed, so return false
        return result != -1;
    }


    // Retrieve all problems
    public Cursor getProblems() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(
                "PROBLEM",
                new String[]{"_id", "order_id", "order_item_id", "problem_description", "date", "status", "note"},
                null, null, null, null, null
        );
    }


    public void updateProblemStatus(int problemId, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", newStatus);

        db.update("PROBLEM", values, "_id = ?", new String[]{String.valueOf(problemId)});
        db.close();
    }

}
