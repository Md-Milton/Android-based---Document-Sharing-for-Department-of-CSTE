package com.cste.milton.student_faculty_document_sharing_system;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class TeacherUpload extends AppCompatActivity {

    private static Button button_select,button_cancel,button_upload;
    private static Spinner spinner;
    ProgressBar progressBar;
    private static TextView notification;
    TextView fileUploading;
    int FILE_SELECT_CODE = 2;
    String fileName;
    FirebaseStorage firebaseStorage;
    FirebaseDatabase firebaseDatabase;
    //DatabaseReference databaseReference;
    //StorageReference storageReference;
    String string = null;
    String displayName;

    String termList[]={"Year 1 Term 1","Year 1 Term 2","Year 2 Term 1","Year 2 Term 2","Year 3 Term 1",
            "Year 3 Term 2","Year 4 Term 1","Year 4 Term 2"};
    ArrayAdapter<String>adapter;

    Uri fileuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_upload);


        button_select = (Button)findViewById(R.id.btn_select);
        notification = (TextView)findViewById(R.id.notification);
        button_upload = (Button)findViewById(R.id.btn_upload);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        fileUploading = (TextView)findViewById(R.id.uploading);
        spinner = (Spinner)findViewById(R.id.spinner);

        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,termList);
        spinner.setAdapter(adapter);


        //storageReference  = FirebaseStorage.getInstance().getReference();
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //databaseReference = FirebaseDatabase.getInstance().getReference();




        button_select.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(ContextCompat.checkSelfPermission(TeacherUpload.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                {
                    selectfile();
                }else ActivityCompat.requestPermissions(TeacherUpload.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},FILE_SELECT_CODE);

            }
        });

        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(fileuri!=null){
                        uploadFile(fileuri);
                    }

                    else Toast.makeText(TeacherUpload.this,"Please select a file",Toast.LENGTH_SHORT).show();
            }
        });


    }

    //end oncreate method

    //start onCreateOptionmenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                finish();
                Intent intent = new Intent(TeacherUpload.this,DepartmentModule.class);
                startActivity(intent);
                default:
                    return super.onOptionsItemSelected(item);

        }
    }








    //end notification


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == FILE_SELECT_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            selectfile();
        }else
            Toast.makeText(TeacherUpload.this,"Please provide permission",Toast.LENGTH_SHORT).show();
    }


    public void selectfile(){
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,FILE_SELECT_CODE);
    }


    public void uploadFile(Uri uriFile){

        String uriString = uriFile.toString();
        File myFile = new File(uriString);

         displayName = null;

        if(uriString.startsWith("content://")){
            Cursor cursor = null;
            try{
                cursor = TeacherUpload.this.getContentResolver().query(uriFile,null,null,null,null);
                if(cursor!= null && cursor.moveToFirst()){
                    displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }finally {
                cursor.close();
            }
        }else if(uriString.startsWith("file://")){
            displayName = myFile.getName();
        }

        fileName = spinner.getSelectedItem().toString()+"";
        final String fileName1 = System.currentTimeMillis()+"";

        StorageReference storageReference = firebaseStorage.getReference();
        storageReference.child("Department File").child(fileName).child(displayName).
//  storageReference = //storageTask = storageReference.
        putFile(uriFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String url = taskSnapshot.getDownloadUrl().toString();//return the url of your uploaded file
                DatabaseReference databaseReference = firebaseDatabase.getInstance().getReference();//return the path to root

                databaseReference.child("Department File").child(fileName).child(fileName1).setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(TeacherUpload.this,"File successfully uploaded",Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(TeacherUpload.this,"File url not successfully uploaded",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TeacherUpload.this,"File not uploaded",Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 *taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                fileUploading.setText("Uploading File To......"+fileName);
                progressBar.setProgress((int)progress);
                if(progress == 100){
                    fileUploading.setText(" ");
                    progressBar.setProgress(0);
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == FILE_SELECT_CODE && resultCode == RESULT_OK && data!= null){
           fileuri = data.getData();
            String uriString = fileuri.toString();
            File myFile = new File(uriString);

            displayName = myFile.getName();

            notification.setText("Selected file:" + displayName);

        }
        else Toast.makeText(TeacherUpload.this,"Please select a file",Toast.LENGTH_SHORT).show();



        super.onActivityResult(requestCode, resultCode, data);
    }
}
