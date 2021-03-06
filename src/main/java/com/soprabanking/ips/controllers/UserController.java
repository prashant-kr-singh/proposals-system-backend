
package com.soprabanking.ips.controllers;



import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soprabanking.ips.repositories.UserRepository;
import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.models.User;

@RestController

@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*")
@RequestMapping("/user")

public class UserController
{
	@Autowired
    private UserRepository userRepository;
	
	@ResponseBody
//	public User dashboard(Principal principal)
//	{
//		
//		String userName=principal.getName();
//		//get the user using username 
//		
//		User user=userRepository.getUserByUserName(userName);
//		//String name = user.getName();
//		//System.out.println("USER"+user);
//	    //return "!!Welcome Landing Page !!" +"Your Informations are :" + "\n" +"userId : " + user.getId()+ "\n" +"name : " + user.getName() + "\n" + "Email : " + user.getEmail()+ "\n"  ;
//	    return user;
//	}
	@GetMapping("/index")
	public ResponseEntity<User> dashboard(Principal principal)
	{
		
		String userName=principal.getName();
		//get the user using username 
		
		User user=userRepository.getUserByUserName(userName);
		//String name = user.getName();
		//System.out.println("USER"+user);
	    //return "!!Welcome Landing Page !!" +"Your Informations are :" + "\n" +"userId : " + user.getId()+ "\n" +"name : " + user.getName() + "\n" + "Email : " + user.getEmail()+ "\n"  ;
	    return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
 
    

	
	
}
	

	

