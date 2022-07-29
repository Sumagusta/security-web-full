/**
 * 
 */
package com.rangga.security.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rangga.security.web.model.Role;
import com.rangga.security.web.model.User;

/**
 * @author ryo rangga sumagusta
 * 
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
