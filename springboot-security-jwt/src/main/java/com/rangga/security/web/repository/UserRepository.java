/**
 * 
 */
package com.rangga.security.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rangga.security.web.model.User;

/**
 * @author ryo rangga sumagusta
 * 
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
}
