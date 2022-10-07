/**
 * 
 */
package com.rangga.security.web.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.rangga.security.web.dto.EmployeeDansDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author ryo rangga sumagusta
 * 
 */
public interface UserServiceRetrofit {

	@GET("/api/recruitment/positions.json")
	Call<List<EmployeeDansDTO>> getEmployeeDanz(); 
	
	@GET("/api/recruitment/positions/{id}")
	Call<EmployeeDansDTO> getEmployeeDanzById(@Path("id") String id); 
	
}
