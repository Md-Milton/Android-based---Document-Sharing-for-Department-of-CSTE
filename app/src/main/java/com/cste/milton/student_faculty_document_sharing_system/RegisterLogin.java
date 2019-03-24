package com.cste.milton.student_faculty_document_sharing_system;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterLogin extends AppCompatActivity implements View.OnClickListener{
    //for the login or register
    private Button buttonSignin;
    private EditText editTextEmail,editTextPassword;
    private TextView textViewSignup;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_login);


        buttonSignin = (Button)findViewById(R.id.buttonSignin);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextpassword);
        textViewSignup = (TextView)findViewById(R.id.textViewSignup);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            //profile activity is here
            //finish();
            //startActivity(new Intent(this,RegisterUpload.class));
        }

        buttonSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);

    }

    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //checking if email and password are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(RegisterLogin.this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(RegisterLogin.this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        //if email and password are not empty displaying a progress dialog;
        progressDialog.setMessage("Signing in please wait a moment....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start the profile activity
                            finish();
                            Toast.makeText(RegisterLogin.this, "Login successfull ", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterLogin.this,RegisterUpload.class));
                        }
                    }
                });
    }
    //for the login
    @Override
    public void onClick(View view) {
        if(view == buttonSignin){
            //startActivity(new Intent(RegisterLogin.this,RegisterUpload.class));
            userLogin();
        }
        if(view == textViewSignup){
            finish();
            startActivity(new Intent(RegisterLogin.this,RegisterActivity.class));
        }
    }

}
