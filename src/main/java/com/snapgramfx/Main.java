package com.snapgramfx;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        // Create a user
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        
        // Save the user
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            System.out.println("User saved successfully!");
            
            // Retrieve the user
            User retrievedUser = session.get(User.class, 1L);
            System.out.println("Retrieved user: " + retrievedUser.getName() + ", " + retrievedUser.getEmail());
            
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        // Close the SessionFactory
        HibernateUtil.shutdown();
    }
}