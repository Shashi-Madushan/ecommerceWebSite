package com.shashimadushan.ecomweb.bo.custom;

import com.shashimadushan.ecomweb.bo.SupperBO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import com.shashimadushan.ecomweb.entity.User;

import java.util.List;

public interface UserBO extends SupperBO {

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId the unique identifier of the user
     * @return the UserDTO object representing the user
     * @throws Exception if the user cannot be found
     */
    UserDTO getUser(String userId) throws Exception;
    List<UserDTO> getAllUsers();
    /**
     * Verifies the user's credentials.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return true if the credentials are valid, false otherwise
     * @throws Exception if verification fails
     */
    UserDTO verifyUser(String username, String password);

    /**
     * Saves a new user or updates an existing user.
     *
     * @param user the UserDTO object containing user details
     * @throws Exception if the user cannot be saved
     */
    void saveUser(UserDTO user) throws Exception;

    /**
     * Deactivates a user by their unique identifier.
     *
     * @param userId the unique identifier of the user
     * @throws Exception if the user cannot be deactivated
     */
    boolean deactivateUser(String userId) throws Exception;
    boolean activateUser(int userId);

}