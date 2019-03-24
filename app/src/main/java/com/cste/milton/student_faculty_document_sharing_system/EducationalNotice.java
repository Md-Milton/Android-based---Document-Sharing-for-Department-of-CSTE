package com.cste.milton.student_faculty_document_sharing_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EducationalNotice extends AppCompatActivity {

    private ListView YearListView;
    private String YearStringList[]={"Year 1 Term 1","Year 1 Term 2","Year 2 Term 1","Year 2 Term 2","Year 3 Term 1",
            "Year 3 Term 2","Year 4 Term 1","Year 4 Term 2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educational_notice);

        YearListView = (ListView)findViewById(R.id.yearListView);

        ArrayAdapter<String>arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1
        ,YearStringList);
        YearListView.setAdapter(arrayAdapter);

        YearListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(EducationalNotice.this,MyRecyclerViewActivity.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm2.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm3.class);
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm4.class);
                    startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm5.class);
                    startActivity(intent);
                }
                if(position == 5){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm6.class);
                    startActivity(intent);
                }
                if(position == 6){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm7.class);
                    startActivity(intent);
                }
                if(position == 7){
                    Intent intent = new Intent(EducationalNotice.this,RecyclerViewTerm8.class);
                    startActivity(intent);
                }
            }
        });

    }
}
