package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DepartmentModule extends AppCompatActivity{
    // button for changing the fragment
    private Button buttonRegister,buttonTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_module);

        // casting for changing the fragment
        buttonRegister = (Button)findViewById(R.id.buttonregister);
        buttonTeacher = (Button)findViewById(R.id.buttonteacher);

        buttonTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(DepartmentModule.this,TeacherLogin.class);
                startActivity(intent);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(DepartmentModule.this,RegisterLogin.class);
                startActivity(intent);
            }
        });

    }
}

