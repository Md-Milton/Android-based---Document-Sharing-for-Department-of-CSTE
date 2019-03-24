package com.cste.milton.student_faculty_document_sharing_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least one digit
                    "(?=.*[a-z])" +         //at least one lower case letter
                    "(?=.*[A-Z])" +         //at least one upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least one special character
                    "(?=\\s+$)" +           //no white spaces
                    ".{6,}" +               ////at least 6 character
                    "$");
    private static Button ButtonRegister;
    private static EditText EditTextEmail,EditTextPassword;
    private static TextView TextViewSignin;

    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        ButtonRegister = (Button)findViewById(R.id.buttonRegister);

        EditTextEmail = (EditText)findViewById(R.id.editTextEmail);
        EditTextPassword = (EditText)findViewById(R.id.editTextpassword);

        TextViewSignin = (TextView)findViewById(R.id.textViewSignin);

        ButtonRegister.setOnClickListener(this);
        TextViewSignin.setOnClickListener(this);

    }

    private void registerTeacher(){
        String email = EditTextEmail.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            //Toast.makeText(getActivity(),"Please enter the email",Toast.LENGTH_LONG).show();
            //stopping the function execution  further
            EditTextEmail.setError("please enter the email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            EditTextEmail.setError("Please enter a valid email");
            return;
        }

        if(TextUtils.isEmpty(password)){
            //pasword is empty
            //Toast.makeText(getActivity(),"please enter the password",Toast.LENGTH_LONG).show();
            //stopping the function execution further
            EditTextPassword.setError("please enter the passord");
            return;
        }if(!PASSWORD_PATTERN.matcher(password).matches()){
            EditTextPassword.setError("password too weak");
            return;
        }

        //if validations are ok
        //we will show a progress bar first
        progressDialog.setMessage("Registering user....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            //we will start the profile activity here
                            //right now how a message
                            Toast.makeText(RegisterActivity.this,"Registered successfully ,now try to login",Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(RegisterActivity.this,"Could not register!please try again",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == ButtonRegister){
            registerTeacher();
        }
        if(view == TextViewSignin){
            //will open login activity here
            finish();
            Intent intent = new Intent(RegisterActivity.this,RegisterLogin.class);
            startActivity(intent);

        }
    }
}
