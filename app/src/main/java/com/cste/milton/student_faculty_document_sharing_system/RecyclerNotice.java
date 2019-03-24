package com.cste.milton.student_faculty_document_sharing_system;

import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;

public class RecyclerNotice extends AppCompatActivity {
    RecyclerView recyclerView;
    String displayName = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_notice);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Department File")
                .child("General Notice");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //atually call for indv items at the database reference
                String fileName = dataSnapshot.getKey();//return the filename
                String url = dataSnapshot.getValue(String.class);//return url for filename
                String uriString = url.toString();
                File myFile = new File(uriString);



                if(uriString.startsWith("content://")){
                    Cursor cursor = null;
                    try{
                        cursor = RecyclerNotice.this.getContentResolver().query(Uri.parse(url),null,null,null,null);
                        if(cursor!= null && cursor.moveToFirst()){
                            displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                        }
                    }finally {
                        cursor.close();
                    }
                }else if(uriString.startsWith("file://")){
                    displayName = myFile.getName();
                }
                ((NoticeAdapter)recyclerView.getAdapter()).update(fileName,url);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //custom adapter
        //populate the recycler view with items
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerNotice.this));
        NoticeAdapter myAdapter = new NoticeAdapter(recyclerView,RecyclerNotice.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);

    }
}
