package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class StudentObjective extends AppCompatActivity {

    Button EducationalNoticebutton,GeneralNoticebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_objective);

        EducationalNoticebutton = (Button)findViewById(R.id.EducationalNoticeId);
        GeneralNoticebutton = (Button)findViewById(R.id.GeneralNoticeId);

        EducationalNoticebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(StudentObjective.this,EducationalNotice.class);
                startActivity(intent);

            }
        });

        GeneralNoticebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(StudentObjective.this,RecyclerNotice.class);
                startActivity(intent);

            }
        });

    }

}

