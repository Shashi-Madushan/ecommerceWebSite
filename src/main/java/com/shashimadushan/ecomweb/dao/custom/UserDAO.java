package com.shashimadushan.ecomweb.dao.custom;

import com.shashimadushan.ecomweb.dao.SupperDAO;
import com.shashimadushan.ecomweb.entity.User;
import java.util.List;

public interface UserDAO extends SupperDAO {

    // Create a new user
    boolean addUser(User user);

    // Retrieve a user by ID
    User getUserById(int userId);

    // Retrieve all users
    List<User> getAllUsers();
     User verifyUser(String username, String password);
    // Update an existing user
    boolean updateUser(User user);

    // Delete a user by ID
    boolean deactivateUser(int userId);
    boolean activateUser(int userId);

}