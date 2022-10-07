/**
 * 
 */
package com.rangga.security.web.service;

import java.util.List;

import com.rangga.security.web.model.Role;
import com.rangga.security.web.model.User;

/**
 * @author ryo rangga sumagusta
 * 
 */
public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUser();
}
