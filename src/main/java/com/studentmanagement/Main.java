package com.studentmanagement;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final StudentService studentService = new StudentService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1 -> addStudent();

                    case 2 -> viewAllStudents();

                    case 3 -> findStudent();

                    case 4 -> updateStudent();

                    case 5 -> deleteStudent();

                    case 6 -> {
                        running = false;
                        System.out.println("Exiting application...");
                    }

                    default -> System.out.println("Invalid choice. Try again.");
                }

            } catch (IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (RuntimeException e) {

                System.out.println("Operation failed: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n==============================");
        System.out.println("   STUDENT MANAGEMENT SYSTEM");
        System.out.println("==============================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Find Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.println("==============================");
    }

    private static void addStudent() {

        System.out.println("\n--- Add Student ---");

        String name = readString("Name: ");
        String email = readString("Email: ");
        String department = readString("Department: ");
        int age = readInt("Age: ");
        String phone = readString("Phone: ");

        Student student = new Student(
                name,
                email,
                department,
                age,
                phone
        );

        studentService.addStudent(student);

        System.out.println("Student added successfully!");
    }

    private static void viewAllStudents() {

        System.out.println("\n--- All Students ---");

        List<Student> students = studentService.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void findStudent() {

        System.out.println("\n--- Find Student ---");

        int id = readInt("Enter student ID: ");

        Student student = studentService.getStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println(student);
        }
    }

    private static void updateStudent() {

        System.out.println("\n--- Update Student ---");

        int id = readInt("Enter student ID: ");

        Student existingStudent = studentService.getStudentById(id);

        if (existingStudent == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("Enter new details:");

        String name = readString("Name: ");
        String email = readString("Email: ");
        String department = readString("Department: ");
        int age = readInt("Age: ");
        String phone = readString("Phone: ");

        Student student = new Student(
                id,
                name,
                email,
                department,
                age,
                phone
        );

        boolean updated = studentService.updateStudent(student);

        if (updated) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student update failed.");
        }
    }

    private static void deleteStudent() {

        System.out.println("\n--- Delete Student ---");

        int id = readInt("Enter student ID: ");

        boolean deleted = studentService.deleteStudent(id);

        if (deleted) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static String readString(String message) {

        System.out.print(message);

        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }
}