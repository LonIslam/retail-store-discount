package com.example.retailstorediscounts.service;

import com.example.retailstorediscounts.dao.UsersDao;
import com.example.retailstorediscounts.entity.User;
import com.example.retailstorediscounts.model.UserModel;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    private final UsersDao usersDao;

    public UsersService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public UserModel createUser(UserModel userModel) {
        User user = new User();
        user.setName(userModel.getName());
        user.setUserType(userModel.getUserType());

        usersDao.save(user);
        userModel.setId(user.getId());
        return userModel;
    }
}
