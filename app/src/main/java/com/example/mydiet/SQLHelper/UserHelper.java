package com.example.mydiet.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mydiet.Model.UserModel;
import com.example.mydiet.R;

public class UserHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = String.valueOf(R.string.app_name);
    private static final int DATABASE_VERSION = 1;
    private static String CREATE_USER_TABLE;
    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String NAMA = "nama";
    private static final String TINGGI = "tinggi";
    private static final String USIA = "usia";
    private static final String BERAT = "berat";
    private static final String JENIS_KELAMIN = "jenis_kelamin";
    private static final String JENIS_AKTIVITAS = "jenis_aktivitas";
    private static final String KALORI = "kalori";

    public UserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAMA +
                " TEXT," + TINGGI + " INTEGER ," + USIA + " INTEGER," + BERAT + " INTEGER," + JENIS_KELAMIN + " INTEGER,"+ JENIS_AKTIVITAS + " DOUBLE,"+  KALORI + " DOUBLE)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

    }

    public long insertData(UserModel userModel){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NAMA,userModel.getNama());
            values.put(TINGGI,userModel.getTinggi());
            values.put(USIA,userModel.getUsia());
            values.put(BERAT,userModel.getBerat());
            values.put(JENIS_KELAMIN,userModel.getJenis_kelamin());
            values.put(JENIS_AKTIVITAS,userModel.getJenis_aktivitas());
            values.put(KALORI,userModel.getKalori());
            long id =  db.insert(TABLE_USER,null,values);

            Cursor dbCursor = db.query(TABLE_USER, null, null, null, null, null, null);
            String[] columnNames = dbCursor.getColumnNames();

            db.close();
            return id;
        }catch (Exception e){
            Log.d("myTag", e.getMessage());
            return  -1;
        }

    }

    public UserModel getUserById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int idUser = 0,tinggi = 0,usia = 0,berat = 0,sex = 0;
        double aktivitas = 0,kalori= 0;
        String name = null;
        Cursor c = db.rawQuery("SELECT * FROM "+ TABLE_USER + " WHERE id=" + id, null);
        if (c.moveToFirst()){
            do {
                // Passing values
                idUser = c.getInt(0);
                name = c.getString(1);
                tinggi = c.getInt(2);
                usia = c.getInt(3);
                berat = c.getInt(4);
                sex = c.getInt(5);
                aktivitas = c.getDouble(6);
                kalori = c.getDouble(7);
                // Do something Here with values
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        UserModel userModel = new UserModel(idUser,name,tinggi,usia,berat,sex,aktivitas,kalori);
        return  userModel ;
    }
}
