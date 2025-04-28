package com.snapgramfx;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Data Access Object for User entity
 * Provides CRUD operations for User objects
 */
public class UserDAO {
    
    /**
     * Create a new user in the database
     * @param user The user to be saved
     * @return The ID of the saved user
     */
    public Long create(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Long userId = null;
        
        try {
            transaction = session.beginTransaction();
            userId = (Long) session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return userId;
    }
    
    /**
     * Retrieve a user by ID
     * @param id The ID of the user to retrieve
     * @return The user object or null if not found
     */
    public User read(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        
        try {
            user = session.get(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return user;
    }
    
    /**
     * Update an existing user
     * @param user The user object with updated values
     * @return true if update was successful, false otherwise
     */
    public boolean update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean success = false;
        
        try {
            transaction = session.beginTransaction();
            session.update(user);
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
     * Delete a user from the database
     * @param user The user to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean success = false;
        
        try {
            transaction = session.beginTransaction();
            session.delete(user);
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
     * Get all users from the database
     * @return List of all users
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = null;
        
        try {
            users = session.createQuery("FROM User").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return users;
    }
}