package com.studentmanagement.service;

import com.studentmanagement.dao.StudentDAO;
import com.studentmanagement.dao.StudentDAOImpl;
import com.studentmanagement.model.Student;

import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAOImpl();
    }

    public void addStudent(Student student) {

        validateStudent(student);

        studentDAO.addStudent(student);
    }

    public List<Student> getAllStudents() {

        return studentDAO.getAllStudents();
    }

    public Student getStudentById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be greater than 0");
        }

        return studentDAO.getStudentById(id);
    }

    public boolean updateStudent(Student student) {

        validateStudent(student);

        if (student.getId() <= 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }

        return studentDAO.updateStudent(student);
    }

    public boolean deleteStudent(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("Student ID must be greater than 0");
        }

        return studentDAO.deleteStudent(id);
    }

    private void validateStudent(Student student) {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if (student.getName() == null || student.getName().isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (student.getEmail() == null || student.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (student.getDepartment() == null || student.getDepartment().isBlank()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }

        if (student.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
    }
}