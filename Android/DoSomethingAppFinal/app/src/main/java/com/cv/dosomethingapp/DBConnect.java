package com.cv.dosomethingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBConnect extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "DoSomething.db";
    private static int DATABASE_VERSION = 1;

    private static String TABLE_NAME = "user" ;
    private static String COLUMN_ID = "_id";
    private static String COLUMN_USERNAME = "username";
    private static String COLUMN_EMAIL = "user_email";
    private static String COLUMN_PASSWORD = "user_password" ;

    public static DBConnect dbConnect = null;

    public DBConnect(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    static DBConnect getInstance(Context context){
        if(dbConnect == null) dbConnect = new DBConnect(context);
        dbConnect.context = context;
        return dbConnect;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s, %s, %s)",
                TABLE_NAME,
                COLUMN_ID,
                COLUMN_USERNAME,
                COLUMN_EMAIL,
                COLUMN_PASSWORD);
        db.execSQL(query);

        String queryActivity = String.format("CREATE TABLE activity(id, activity_title, activity_type, activity_detail)");
        db.execSQL(queryActivity);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String username, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("user_password", password);
        contentValues.put("user_email", email);
        long result = MyDB.insert("user", null, contentValues);
        System.err.println("Data inserted");
        if(result==-1) return false;
        else return true;
    }

    public Boolean insertActivity(String id, String activity_title, String activity_type, String activity_detail){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("activity_title", activity_title);
        contentValues.put("activity_type", activity_type);
        contentValues.put("activity_detail", activity_detail);
        long result = MyDB.insert("activity", null, contentValues);
        System.err.println("Data inserted");
        if(result==-1) return false;
        else return true;
    }

    public ArrayList<Activity> getActivity(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM activity WHERE id=?", new String[] {GlobalStorage.user.getId().toString()});
        ArrayList<Activity> activityArrayList = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {

            activityArrayList.add(new Activity(cursor.getString(1), cursor.getString(2), cursor.getInt(3)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        System.out.println("[Debug JEV2]: " + activityArrayList.size());
        return activityArrayList;
    }

    public void getUser(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE user_email =?", new String[] {email});
        User user = null;

        if(cursor.moveToFirst()){
            user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        cursor.close();
        GlobalStorage.user = user;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=?", new String[] {username});
        if(cursor.getCount() >= 1) return true;
        else return false;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE user_email = ? AND user_password = ?", new String[] {email, password});
        if(cursor.getCount() >= 1) return true;
        else return false;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE user_email=?", new String[] {email});
        if(cursor.getCount() > 1) return true;
        else return false;
    }
}
