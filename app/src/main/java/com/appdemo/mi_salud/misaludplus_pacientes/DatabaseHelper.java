package com.appdemo.mi_salud.misaludplus_pacientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import com.appdemo.mi_salud.misaludplus_pacientes.Datos.datosPaciente;



public class DatabaseHelper extends SQLiteOpenHelper{

    /*
    *       ESTRUCTURA DE LA TABLA PACIENTES
    * */
    public static abstract class estructura implements BaseColumns{
        private static final String TABLE_NAME = "pacientes";

        private static final String COLUMN_ID = "id";
        private static final String COLUMN_USERNAME = "username";
        private static final String COLUMN_PASS = "pass";
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";
    private SQLiteDatabase db;

    private static final String TABLE_CREATE ="CREATE TABLE "+estructura.TABLE_NAME+"("+
            estructura.COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            estructura.COLUMN_USERNAME+" TEXT NOT NULL, "+
            estructura.COLUMN_PASS+" TEXT NOT NULL, "+"UNIQUE ("+estructura.COLUMN_ID+"));";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query= "DROP TABLE IF EXISTS "+estructura.TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public ContentValues convert2CV(datosPaciente data){
        String query = "SELECT * FROM "+estructura.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        ContentValues values = new ContentValues();
        values.put(estructura.COLUMN_ID,count);
        values.put(estructura.COLUMN_USERNAME,String.valueOf(data.getNumDoc()));
        values.put(estructura.COLUMN_PASS, String.valueOf(data.getPsswrd()));

        return values;
    }


    public void insertContact (datosPaciente c){
        db = this.getWritableDatabase();
        db.insert(estructura.TABLE_NAME, null, convert2CV(c));
        db.close();
    }

    public void insertUser (String name, String psw){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "SELECT * FROM "+estructura.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();

        values.put(estructura.COLUMN_ID,count);
        values.put(estructura.COLUMN_USERNAME,name);
        values.put(estructura.COLUMN_PASS, psw);

        db.insert(estructura.TABLE_NAME, null, values);
        cursor.close();
        db.close();
    }

    public String searchPass (String uname){
        db = getReadableDatabase();
        String query = "SELECT "+estructura.COLUMN_USERNAME+" , "+estructura.COLUMN_PASS+" FROM "+estructura.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b="";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);
                if(a.equals(uname)){
                    b=cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return b;
    }
}
