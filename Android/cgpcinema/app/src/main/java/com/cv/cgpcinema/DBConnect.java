package com.cv.cgpcinema;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnect extends SQLiteOpenHelper {

    private Context context;
    private static String DATABASE_NAME = "cgpcinema.db";
    private static int DATABASE_VERSION = 1;

    private static String TABLE_NAME = "movieOrdered" ;
    private static String COLUMN_ID = "_id";
    private static String COLUMN_TITLE = "title";
    private static String COLUMN_SEAT = "seat";
    private static String COLUMN_DATE = "date" ;

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
                COLUMN_SEAT,
                COLUMN_TITLE,
                COLUMN_DATE);
        db.execSQL(query);

        String queryActivity = String.format("CREATE TABLE activity(id, activity_title, activity_type, activity_detail)");
        db.execSQL(queryActivity);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String title, String seat, String date){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);
        contentValues.put("seat", seat);
        contentValues.put("date", date);

        long result = MyDB.insert("movie", null, contentValues);

        if(result==-1) return false;
        else return true;
    }
    public Movie getMovie(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM movie WHERE title =?", new String[] {title});

        Movie movie = null;

        if(cursor.moveToFirst()){
            movie = new Movie(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }
        cursor.close();
        return movie;
    }
}
