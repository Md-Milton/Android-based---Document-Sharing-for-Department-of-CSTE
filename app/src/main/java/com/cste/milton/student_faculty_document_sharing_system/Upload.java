package com.cste.milton.student_faculty_document_sharing_system;

public class Upload {
    public String url;

    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String url) {

        this.url= url;
    }

//    public String getName() {
//        return name;
//    }

    public String getUrl() {
        return url;
    }
}
