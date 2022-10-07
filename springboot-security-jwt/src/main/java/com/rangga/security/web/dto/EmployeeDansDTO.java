/**
 * 
 */
package com.rangga.security.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author ryo rangga sumagusta
 * 
 * @since Oct 7, 2022
 */
@Data
public class EmployeeDansDTO {

	private String id;
	private String type;
	private String url;

	@JsonProperty("created_at")
	private String createdAt;
	
	private String company;
	
	@JsonProperty("company_url")
	private String companyUrl;
	
	private String location;
	private String title;
	private String description;
	
	@JsonProperty("how_to_apply")
	private String howToApply;
	
	@JsonProperty("company_logo")
	private String companyLogo;
	
	
}
