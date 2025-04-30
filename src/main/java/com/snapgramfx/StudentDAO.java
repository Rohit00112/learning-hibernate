package com.snapgramfx;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Data Access Object for Student entity
 * Provides CRUD operations for Student objects
 */
public class StudentDAO {
    
    /**
     * Create a new student in the database
     * @param student The student to be saved
     * @return The ID of the saved student
     */
    public Long create(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long studentId = null;
        
        try {
            transaction = session.beginTransaction();
            studentId = (Long) session.save(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return studentId;
    }
    
    /**
     * Retrieve a student by ID
     * @param id The ID of the student to retrieve
     * @return The student object or null if not found
     */
    public Student read(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student student = null;
        
        try {
            student = session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return student;
    }
    
    /**
     * Update an existing student
     * @param student The student object with updated values
     * @return true if update was successful, false otherwise
     */
    public boolean update(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean success = false;
        
        try {
            transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return success;
    }
    
    /**
     * Delete a student from the database
     * @param student The student to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean delete(Student student) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean success = false;
        
        try {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return success;
    }
    
    /**
     * Get all students from the database
     * @return List of all students
     */
    @SuppressWarnings("unchecked")
    public List<Student> getAllstudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> students = null;
        
        try {
            students = session.createQuery("FROM Student").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return students;
    }
}