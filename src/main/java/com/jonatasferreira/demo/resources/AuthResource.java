package com.jonatasferreira.demo.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jonatasferreira.demo.constants.ApiEndpoints;
import com.jonatasferreira.demo.security.JWTUtil;
import com.jonatasferreira.demo.security.UserSS;
import com.jonatasferreira.demo.services.UserService;

@RestController
@RequestMapping(value = ApiEndpoints.ENDPOINT_AUTH)
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse res) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		res.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
}
