package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherRegister extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper databaseHelper;
    TextView textView;


    EditText nameId, passwordId, emailId;
    Button regButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        nameId = (EditText) findViewById(R.id.editTextname);
        passwordId = (EditText) findViewById(R.id.editTextpassword);
        emailId = (EditText) findViewById(R.id.editTextEmail);

        regButton = (Button) findViewById(R.id.buttonRegister);

        textView = (TextView)findViewById(R.id.textViewSignin);
        regButton.setOnClickListener(this);
        textView.setOnClickListener(this);


    }

    public void Register(){
        String email = emailId.getText().toString().trim();
        if(nameId.length() == 0){
            nameId.setError("Enter your name");
            return;
        }
         if(passwordId.length() == 0){
            passwordId.setError("Enter your password");
            return;
        }if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailId.setError("Please enter a valid email");
            return;
        }

        String name = nameId.getText().toString();
        String pass = passwordId.getText().toString();

        long rowId = databaseHelper.insertData(name, pass, email);
        if (rowId == -1) {
            Toast.makeText(getApplicationContext(), "Registration Unsuccessfull ", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "Registration " + rowId + "is successfully ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        if (v == regButton) {
            Register();
        }
        if (v == textView) {
            finish();
            Intent intent = new Intent(TeacherRegister.this, TeacherLogin.class);
            startActivity(intent);
        }

    }
}
