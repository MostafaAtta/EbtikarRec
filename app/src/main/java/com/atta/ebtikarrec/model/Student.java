package com.atta.ebtikarrec.model;

public class Student {

    int id, studentClass;
    String studentName, imgUrl, email, mobileNumber, gender;
    double gpa;

    public Student(int id, int studentClass, String studentName, String imgUrl, String email, String mobileNumber, String gender, double gpa) {
        this.id = id;
        this.studentClass = studentClass;
        this.studentName = studentName;
        this.imgUrl = imgUrl;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.gpa = gpa;
    }

    public Student(int id, String studentName, String imgUrl) {
        this.id = id;
        this.studentName = studentName;
        this.imgUrl = imgUrl;
    }

    public Student(int id, String studentName) {

        this.id = id;
        this.studentName = studentName;
    }

    public int getId() {
        return id;
    }

    public int getStudentClass() {
        return studentClass;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public double getGpa() {
        return gpa;
    }
}
