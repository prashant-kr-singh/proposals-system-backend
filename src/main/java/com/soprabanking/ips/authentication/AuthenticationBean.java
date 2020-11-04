package com.soprabanking.ips.authentication;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import com.soprabanking.ips.models.User;
import com.soprabanking.ips.repositories.UserRepository;

public class AuthenticationBean {
	private String message;
    public AuthenticationBean(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }
	
	
	
	
	

	
	
}