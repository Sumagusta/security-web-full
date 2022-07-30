/**
 * 
 */
package com.rangga.security.web.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rangga.security.web.model.Role;
import com.rangga.security.web.model.User;
import com.rangga.security.web.repository.RoleRepository;
import com.rangga.security.web.repository.UserRepository;
import com.rangga.security.web.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ryo rangga sumagusta
 * 
 * @since Jul 28, 2022
 */

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		log.info("simpan user baru {} ke db", user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		
		log.info("simpan role baru {} ke db", role.getName());
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		
		log.info("simpan role baru {} ke user {}", roleName, username);
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
		
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		
		log.info("Fetching user {}", username);
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		
		log.info("Fetching all users");
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		if (user == null) {
			log.error("User not found in the db");
			throw new UsernameNotFoundException("User not found in db");
		}else {
			log.info("User found in the db : {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
