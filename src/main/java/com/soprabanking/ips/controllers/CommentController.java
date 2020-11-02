package com.soprabanking.ips.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/comments")
	public ResponseEntity<List<Comment>> displayComments(@RequestParam Long proposalId)
	{
		return new ResponseEntity<List<Comment>>(commentService.displayComments(proposalId),HttpStatus.OK);
	}
	
	@PostMapping("/newcomment")
	public ResponseEntity<Comment> addComment(@RequestParam Comment com)
	{
		return new ResponseEntity<Comment>(commentService.addComment(com),HttpStatus.OK);

	}
   
}
