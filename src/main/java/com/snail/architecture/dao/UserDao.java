package com.snail.architecture.dao;

import com.snail.architecture.entity.User;

import java.util.List;

/**
 * <p>User: Snail
 * <p>Date: 16-1-11
 * <p>Version: 1.0
 */
public interface UserDao {

    public int createUser(User user);
    public int updateUser(User user);
    public int updateUserpassword(User user);
    public void deleteUser(Long userId);

    User findOne(Long userId);

    List<User> findAll();

    User findByUsername(String username);

}
