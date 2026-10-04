package com.studentmanagement.dao;

import com.studentmanagement.model.Student;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentDAOTest {

    private static StudentDAO studentDAO;
    private static int studentId;

    @BeforeAll
    static void setup() {
        studentDAO = new StudentDAOImpl();
    }

    @Test
    @Order(1)
    void testAddStudent() {

        Student student = new Student(
                "JUnit Student",
                "junit@test.com",
                "Computer Science",
                21,
                "9876543210"
        );

        studentDAO.addStudent(student);

        List<Student> students = studentDAO.getAllStudents();

        Student createdStudent = students.stream()
                .filter(s -> s.getEmail().equals("junit@test.com"))
                .findFirst()
                .orElse(null);

        assertNotNull(createdStudent);

        studentId = createdStudent.getId();

        assertEquals("JUnit Student", createdStudent.getName());
    }

    @Test
    @Order(2)
    void testGetStudentById() {

        Student student = studentDAO.getStudentById(studentId);

        assertNotNull(student);
        assertEquals(studentId, student.getId());
        assertEquals("junit@test.com", student.getEmail());
    }

    @Test
    @Order(3)
    void testUpdateStudent() {

        Student student = new Student(
                studentId,
                "Updated JUnit Student",
                "junit.updated@test.com",
                "Information Technology",
                22,
                "9876543211"
        );

        boolean updated = studentDAO.updateStudent(student);

        assertTrue(updated);

        Student updatedStudent =
                studentDAO.getStudentById(studentId);

        assertNotNull(updatedStudent);
        assertEquals("Updated JUnit Student", updatedStudent.getName());
        assertEquals("Information Technology",
                updatedStudent.getDepartment());
    }

    @Test
    @Order(4)
    void testDeleteStudent() {

        boolean deleted = studentDAO.deleteStudent(studentId);

        assertTrue(deleted);

        Student student =
                studentDAO.getStudentById(studentId);

        assertNull(student);
    }
}