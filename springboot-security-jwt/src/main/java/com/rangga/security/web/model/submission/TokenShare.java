/**
 * 
 */
package com.rangga.security.web.model.submission;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ryo rangga sumagusta
 * 
 * @since Aug 5, 2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TokenShare {

	private String getAccessToken;
	private String getRefreshToken;
	
}
