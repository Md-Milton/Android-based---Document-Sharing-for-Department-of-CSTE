package com.cste.milton.student_faculty_document_sharing_system;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerViewTerm8 extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_term8);


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Department File")
                .child("Year 4 Term 2");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //atually call for indv items at the database reference
                String fileName = dataSnapshot.getKey();//return the filename
                String url = dataSnapshot.getValue(String.class);//return url for filename
                ((MyAdapter)recyclerView.getAdapter()).update(fileName,url);
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
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerViewTerm8.this));
        MyAdapter myAdapter = new MyAdapter(recyclerView,RecyclerViewTerm8.this,new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(myAdapter);


    }
}
