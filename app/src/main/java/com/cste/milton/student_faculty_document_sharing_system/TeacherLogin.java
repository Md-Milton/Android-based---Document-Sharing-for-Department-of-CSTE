package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherLogin extends AppCompatActivity implements View.OnClickListener{


    DatabaseHelper databaseHelper;
    TextView textView;
    EditText emailid,passid;
    Button loginButton;
    Cursor cursor;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        databaseHelper = new DatabaseHelper(this);
        db = databaseHelper.getWritableDatabase();
        textView = (TextView)findViewById(R.id.textViewSignup);
        emailid = (EditText) findViewById(R.id.editTextEmail);
        passid = (EditText) findViewById(R.id.editTextpassword);
        loginButton = (Button) findViewById(R.id.buttonSignin);

        loginButton.setOnClickListener(this);
        textView.setOnClickListener(this);

    }

    public void login(){
        String email = emailid.getText().toString().trim();
        String pass = passid.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(TeacherLogin.this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(TeacherLogin.this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        cursor = db.rawQuery(" SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.EMAIL + " =? AND " + DatabaseHelper.PASSWORD + "=?", new String[]{email, pass});
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToNext();
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(TeacherLogin.this,TeacherUpload.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login not successful", Toast.LENGTH_SHORT).show();
            }
        }

    }



    @Override
    public void onClick(View v) {
        if(v ==loginButton) {
            login();
        }
        if(v == textView){
            finish();
            Intent intent = new Intent(TeacherLogin.this,TeacherRegister.class);
            startActivity(intent);
        }

    }

}
