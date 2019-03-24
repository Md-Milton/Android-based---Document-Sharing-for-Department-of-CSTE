package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ContactsObjective extends AppCompatActivity {

    Button buttoncr,buttonteacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_objective);

        buttoncr = (Button) findViewById(R.id.buttoncr);
        buttonteacher = (Button) findViewById(R.id.buttonteacher);

        buttonteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ContactsObjective.this,TeacherInfo.class);
                startActivity(intent);
            }
        });

        buttoncr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(ContactsObjective.this,cr_info.class);
                startActivity(intent);
            }
        });
    }
}
