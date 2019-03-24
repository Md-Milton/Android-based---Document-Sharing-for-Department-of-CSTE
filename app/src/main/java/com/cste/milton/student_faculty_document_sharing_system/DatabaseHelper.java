package com.cste.milton.student_faculty_document_sharing_system;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;



public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "student_details";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";

    public static final String CREATE_TABLE = "CREATE TABLE " +
            TABLE_NAME + " ( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PASSWORD + " TEXT, " +
            EMAIL + " TEXT " +
            ")";
    private Context context;


    public static final String DROP_TABLE = " DROP TABLE IF EXISTS "+ TABLE_NAME;



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            Toast.makeText(context,"Exception :onCreate",Toast.LENGTH_SHORT).show();
            db.execSQL(CREATE_TABLE);
        }catch(Exception e){
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            Toast.makeText(context,"onUpgrade",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch(Exception e){
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(String name,String pass,String email){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(PASSWORD,pass);
        contentValues.put(EMAIL,email);

        long rowId = db.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }
}



