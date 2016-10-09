/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.crce.wtlabs.dao;

import java.sql.ResultSet;
import org.crce.wtlabs.dto.User;

/**
 *
 * @author Flav
 */
public interface UserDao {
    
    public boolean isValid(User user);
    public int getUserType(User user);
}
