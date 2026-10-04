package com.studentmanagement.dao;

import com.studentmanagement.model.Student;
import com.studentmanagement.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public void addStudent(Student student) {

        String sql = """
                INSERT INTO students
                (name, email, department, age, phone)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getDepartment());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getPhone());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to add student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {

        String sql = "SELECT * FROM students";

        List<Student> students = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("department"),
                        resultSet.getInt("age"),
                        resultSet.getString("phone")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve students", e);
        }

        return students;
    }

    @Override
    public Student getStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Student(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("department"),
                            resultSet.getInt("age"),
                            resultSet.getString("phone")
                    );
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to find student", e);
        }

        return null;
    }

    @Override
    public boolean updateStudent(Student student) {

        String sql = """
                UPDATE students
                SET name = ?, email = ?, department = ?, age = ?, phone = ?
                WHERE id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getDepartment());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getPhone());
            statement.setInt(6, student.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to update student", e);
        }
    }

    @Override
    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete student", e);
        }
    }
}