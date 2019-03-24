package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    CardView Department,Student,Contacts,Photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Department = (CardView)findViewById(R.id.department);
        Student = (CardView)findViewById(R.id.student);
        Contacts = (CardView)findViewById(R.id.contacts);
        Photo = (CardView)findViewById(R.id.photo);

        Department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,DepartmentModule.class);
                startActivity(intent);
            }
        });

        Student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,StudentObjective.class);
                       startActivity(intent);
            }
        });
        Contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ContactsObjective.class);
                startActivity(intent);
            }
        });
        Photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,PhotoGallery.class);
                startActivity(intent);
            }
        });
    }
}
