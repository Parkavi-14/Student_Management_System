package com.studentmanagement.model;

public class Student {

    private int id;
    private String name;
    private String email;
    private String department;
    private int age;
    private String phone;

    public Student() {
    }

    public Student(String name, String email, String department, int age, String phone) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.age = age;
        this.phone = phone;
    }

    public Student(int id, String name, String email, String department, int age, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.age = age;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}