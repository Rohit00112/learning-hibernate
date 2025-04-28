package com.snapgramfx;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize UserDAO
        UserDAO userDAO = new UserDAO();
        
        // 1. CREATE - Create a new user
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        
        Long userId = userDAO.create(user);
        System.out.println("User created with ID: " + userId);
        
        // 2. READ - Retrieve the user
        User retrievedUser = userDAO.read(1L);
        if (retrievedUser != null) {
            System.out.println("Retrieved user: " + retrievedUser.getName() + ", " + retrievedUser.getEmail());
        }
        
        // 3. UPDATE - Update the user
        retrievedUser.setName("Jane Doe");
        retrievedUser.setEmail("jane.doe@example.com");
        boolean updateSuccess = userDAO.update(retrievedUser);
        System.out.println("User update status: " + (updateSuccess ? "Success" : "Failed"));
        
        // Verify update
        User updatedUser = userDAO.read(1L);
        if (updatedUser != null) {
            System.out.println("Updated user: " + updatedUser.getName() + ", " + updatedUser.getEmail());
        }
        
        // Create another user for demonstration
        User secondUser = new User();
        secondUser.setId(2L);
        secondUser.setName("Bob Smith");
        secondUser.setEmail("bob.smith@example.com");
        userDAO.create(secondUser);
        
        // Get all users
        List<User> allUsers = userDAO.getAllUsers();
        System.out.println("\nAll users in database:");
        for (User u : allUsers) {
            System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail());
        }
        
        // 4. DELETE - Delete the first user
        boolean deleteSuccess = userDAO.delete(retrievedUser);
        System.out.println("\nUser deletion status: " + (deleteSuccess ? "Success" : "Failed"));
        
        // Verify deletion
        allUsers = userDAO.getAllUsers();
        System.out.println("\nRemaining users in database:");
        for (User u : allUsers) {
            System.out.println(u.getId() + ": " + u.getName() + ", " + u.getEmail());
        }
        
        // Close the SessionFactory
        HibernateUtil.shutdown();
    }
}