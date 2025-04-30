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
            Student student = new Student();
           student.setName("Sujan Subedi");
           student.setEmail("sujan@gmail.com");
           session.save(student);


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