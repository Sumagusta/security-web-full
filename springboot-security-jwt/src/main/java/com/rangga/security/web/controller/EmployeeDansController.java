/**
 * 
 */
package com.rangga.security.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rangga.security.web.dto.EmployeeDansDTO;
import com.rangga.security.web.service.UserServiceRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author ryo rangga sumagusta
 * 
 */
@RestController
@RequestMapping("/api/dans")
public class EmployeeDansController {
	
	Retrofit retrofit = new Retrofit.Builder().baseUrl("http://dev3.dansmultipro.co.id")
			.addConverterFactory(JacksonConverterFactory.create()).build();
	UserServiceRetrofit service = retrofit.create(UserServiceRetrofit.class);
	
	@RequestMapping("/get/employee")
	public List<EmployeeDansDTO> getEmployeeDans() throws IOException{
		
		List<EmployeeDansDTO> employee = service.getEmployeeDanz().execute().body();
		
		return employee;
	}
	
	@GetMapping("/get/employee/{id}")
	public EmployeeDansDTO getEmployeeDetail(@PathVariable String id) throws IOException {
		EmployeeDansDTO employee = service.getEmployeeDanzById(id).execute().body();
		return employee;
	}
	
}
