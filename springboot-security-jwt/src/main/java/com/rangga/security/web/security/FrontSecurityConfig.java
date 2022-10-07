/**
 * 
 */
package com.rangga.security.web.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import lombok.RequiredArgsConstructor;

/**
 * @author ryo rangga sumagusta
 * 
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(2)
public class FrontSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/home")
				.hasAuthority("ROLE_USER").and().rememberMe().userDetailsService(userDetailService).and()
				.formLogin(form -> form.defaultSuccessUrl("/home").loginPage("/login").failureUrl("/login?error=true"))
				.logout(logout -> logout.deleteCookies("dummyCookie"));
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**");
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		authenticationProvider.setUserDetailsService(userDetailService);
		return authenticationProvider;
	}

	
//	protected AuthenticationProvider authProvider()  {
//		// TODO Auto-generated method stub
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // DaoAuthenticationProvider, implementasi yang digunakan untuk mengambil detail user dari userDetailService
//		provider.setUserDetailsService(userDetailService);
//		provider.setPasswordEncoder(bCryptPasswordEncoder);
//		return provider;
//	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}

}
