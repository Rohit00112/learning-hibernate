package com.snapgramfx;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataPopulator {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Create dummy users
            User user1 = new User();
            user1.setId(1L);
            user1.setName("John Doe");
            user1.setEmail("john@example.com");
            session.save(user1);

            User user2 = new User();
            user2.setId(2L);
            user2.setName("Jane Smith");
            user2.setEmail("jane@example.com");
            session.save(user2);

            User user3 = new User();
            user3.setId(3L);
            user3.setName("Bob Wilson");
            user3.setEmail("bob@example.com");
            session.save(user3);

            User user4 = new User();
            user4.setId(4L);
            user4.setName("Alice Brown");
            user4.setEmail("alice@example.com");
            session.save(user4);

            User user5 = new User();
            user5.setId(5L);
            user5.setName("Charlie Davis");
            user5.setEmail("charlie@example.com");
            session.save(user5);

            transaction.commit();
            System.out.println("Dummy data inserted successfully!");

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
}