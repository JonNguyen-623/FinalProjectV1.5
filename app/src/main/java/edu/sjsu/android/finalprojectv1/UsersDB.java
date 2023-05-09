package edu.sjsu.android.finalprojectv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class UsersDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "usersDatabase";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String ID = "_id";
    public static final String EMAIL = "email";
    static final String FULLNAME = "fullname";
    private static final String MOBILE = "mobile";
    static final String PASSWORD ="password";

    static final String CREATE_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " ("+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + FULLNAME + " TEXT NOT NULL, "
                    + EMAIL + " TEXT NOT NULL, "
                    + MOBILE + " TEXT NOT NULL, "
                    + PASSWORD + " TEXT NOT NULL);";

    public UsersDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insert(ContentValues contentValues) {
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getUser(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//            sqLiteDatabase.execSQL(
//                    "SELECT * FROM " + TABLE_NAME + " WHERE email = ? AND password = ?",
//                    new String[]{email, password});
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[] {"fullname, email, mobile, password"},
                "email = ? AND password = ?", new String[]{email, password}, null, null, null);
        return cursor;
    }



    public Boolean checkEmail(String email) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE email = ?", new String[]{email});
        if(cursor.getColumnCount() > 0)
            return true;
        else
            return false;
    }

}
