package com.studentmanagement.dao;

import com.studentmanagement.model.Student;

import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    boolean updateStudent(Student student);

    boolean deleteStudent(int id);
}