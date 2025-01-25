package com.shashimadushan.ecomweb.bo.impl;

import com.shashimadushan.ecomweb.bo.custom.UserBO;
import com.shashimadushan.ecomweb.dao.DAOFactory;
import com.shashimadushan.ecomweb.dao.custom.UserDAO;
import com.shashimadushan.ecomweb.dao.impl.UserDAOImpl;
import com.shashimadushan.ecomweb.dto.ProductDTO;
import com.shashimadushan.ecomweb.dto.UserDTO;
import com.shashimadushan.ecomweb.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOTypes.USER);
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDTO getUser(String userId) throws Exception {
      User user =  userDAO.getUserById(Integer.parseInt(userId));
      return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>() {}.getType());

    }

    @Override
    public UserDTO verifyUser(String username, String password) {
      User user= userDAO.verifyUser(username, password);
      return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public void saveUser(UserDTO user) throws Exception {
        userDAO.addUser(modelMapper.map(user, User.class));
    }

    @Override
    public boolean deactivateUser(String userId) throws Exception {
        return     userDAO.deactivateUser(Integer.parseInt(userId));
    }

    @Override
    public boolean activateUser(int userId) {
        return userDAO.activateUser(userId);
    }

    @Override
    public boolean updateUser(UserDTO user) throws Exception {
        return userDAO.updateUser(modelMapper.map(user, User.class));
    }
}
