package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import student.Login;

public class DBManager extends SQLiteOpenHelper {
    private Context context;
    private static final String TAG = "DBManager";

    public DBManager(@Nullable Context context) {
        super(context, DBQuery.DATABASE_NAME, null, DBQuery.DATABASE_VERSION);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBQuery.SQL_CREATE_TABLE);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
    }

    public void addLogin(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBQuery.KEY_USER_NAME, login.getUserName());
        values.put(DBQuery.KEY_PASSWORD, login.getPassword());
        db.insert(DBQuery.TABLE_NAME, null, values);
        db.close();
    }

    public Boolean getLogin(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DBQuery.TABLE_NAME, null, DBQuery.KEY_USER_NAME + " =? AND "
                            + DBQuery.KEY_PASSWORD + "=?",
                            new String[]{userName, password}, null, null, null);
        cursor.moveToFirst();
        Login login = new Login(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        if (!login.getUserName().isEmpty()){
            return true;
        }
        return false;
    }

    public ArrayList<Login> getAllInfoLogin() {
        ArrayList<Login> logins = new ArrayList<>();
        String query = DBQuery.SELECT_DATA + DBQuery.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Login login = new Login();
                login.setUserName(cursor.getString(1));
                logins.add(login);
            } while (cursor.moveToNext());
        }
        db.close();
        return logins;
    }

    public void updateLogin(Login login) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBQuery.KEY_USER_NAME, login.getUserName());
        values.put(DBQuery.KEY_PASSWORD, login.getPassword());
        db.update(DBQuery.TABLE_NAME, values, DBQuery.KEY_ID + "=?",
                new String[]{String.valueOf(login.getId())});
        db.close();
    }

    public void deleteLogin(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBQuery.TABLE_NAME, DBQuery.KEY_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
    }
}

