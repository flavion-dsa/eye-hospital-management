/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dao;

import org.crce.wtlabs.dto.User;

/**
 *
 * @author Flav
 */
public interface UserDao {
    
    public boolean isValid(User user);
    public boolean isVerified(User user);
    public User getUser(String name);
    public void addUser(User user);
    public void updateUser(User user);
    public void updatePassword(User user);
}
