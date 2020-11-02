package com.soprabanking.ips.controllers;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprabanking.ips.models.Comment;
import com.soprabanking.ips.services.CommentService;

@RestController
@RequestMapping(value={"/feed/all","/feed/team","/feed/user"})
public class CommentController 
{
	@Autowired
   private CommentService commentService;
	
	@GetMapping(value="/comments",consumes =APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Comment>> displayComments(@RequestBody String body)
	{
		try
		{
		return new ResponseEntity<List<Comment>>(commentService.displayComments(body),HttpStatus.OK);
		}
		
		catch(Exception ex) 
		{
			return new ResponseEntity<List<Comment>>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(value="/newcomment",consumes =APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addComment(@RequestBody Comment body)
	{
		try
		{
			Comment saved= commentService.addComment(body);
		    return new ResponseEntity<String>("Posted comment '"+saved.getComment()+"'",HttpStatus.OK);
         
		}
		catch(Exception ex)
		{
			return new ResponseEntity<String>("Comment not posted", HttpStatus.NOT_ACCEPTABLE);
		}
	}
   
}