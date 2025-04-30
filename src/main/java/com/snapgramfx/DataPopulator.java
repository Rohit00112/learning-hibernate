package com.snapgramfx;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Scanner;

public class DataPopulator {

    private static final StudentDAO studentDAO = new StudentDAO();

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Scanner scanner = new Scanner(System.in);
//            show options to add, update, delete, read, exit
            while (true) {
                System.out.println("1. Add Student");
                System.out.println("2. Update Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Read Student");
                System.out.println("5. Read All Students");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter student name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter student email: ");
                        String email = scanner.nextLine();
                        addStudent(name, email);
                        break;
                    case 2:
                        System.out.print("Enter student ID: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new student name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new student email: ");
                        String newEmail = scanner.nextLine();
                        updateStudent(id, newName, newEmail);
                        break;
                    case 3:
                        System.out.print("Enter student ID: ");
                        Long deleteId = scanner.nextLong();
                        deleteStudent(deleteId);
                        break;
                    case 4:
                        System.out.print("Enter student ID: ");
                        Long readId = scanner.nextLong();
                        readStudent(readId);
                        break;
                    case 5:
                        getAllStudents();
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            HibernateUtil.shutdown();
        }
    }

    private static void addStudent(String name, String email) {
        Student existingStudent = studentDAO.readByEmail(email);
        if (existingStudent != null) {
            System.out.println("Student with email " + email + " already exists.");
        } else {
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            studentDAO.create(student);
            System.out.println("Student added successfully.");
        }

    }

    private static void updateStudent(Long id, String name, String email) {
        Student student = readStudent(id);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            studentDAO.update(student);
            System.out.println("Student updated successfully.");
        }
    }

    private static void deleteStudent(Long id) {
        Student student = readStudent(id);
        if (student != null) {
            studentDAO.delete(student);
            System.out.println("Student deleted successfully.");
        }
    }

    private static Student readStudent(Long id) {
        Student student = studentDAO.read(id);
        if (student != null) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + ", Email: " + student.getEmail());
        } else {
            System.out.println("Student not found.");
        }
        return student;
    }

    private static void getAllStudents() {
        List<Student> students = studentDAO.getAllstudents();
        for (Student student : students) {
            System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName() + ", Email: " + student.getEmail());
        }
    }

    private static Student readByEmail(String email) {
        return studentDAO.readByEmail(email);
    }
}