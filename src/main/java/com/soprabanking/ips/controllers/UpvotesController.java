package com.soprabanking.ips.controllers;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

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

import com.soprabanking.ips.models.Upvotes;
import com.soprabanking.ips.services.UpvotesService;

@RestController
@RequestMapping(value= {"/feed/all","/feed/user","/feed/team"})
public class UpvotesController
{
	@Autowired
	private UpvotesService upvotesService;
	
    @GetMapping("/upvotes")
	public ResponseEntity<List<Upvotes>> displayUpvotes(@RequestParam Long proposalId)
	{
    	return new ResponseEntity<List<Upvotes>>(upvotesService.listUpvotes(proposalId),HttpStatus.OK);
	}
    
    @PostMapping(value="/newupvote",consumes =APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> upvoteProposal(@RequestBody Upvotes upvote)
    {
    	try
    	{
    		upvotesService.upvoteProposal(upvote);
    	return new ResponseEntity<String>("Saved your upvote",HttpStatus.OK);
        
    	}
    	catch(Exception e)
    	{
    		return new ResponseEntity<String>("Unable to upvote proposal",HttpStatus.NOT_ACCEPTABLE);
    	}
    	}
    public ResponseEntity<String> reverseupvoteProposal(@RequestBody Upvotes upvote)
    {
         
         try
     	{
        	 upvotesService.reverseUpvote(upvote);
     	return new ResponseEntity<String>("Reversed your upvote",HttpStatus.OK);
         
     	}
     	catch(Exception e)
     	{
     		return new ResponseEntity<String>("Unable to reverse the upvote on proposal",HttpStatus.NOT_ACCEPTABLE);
     	}
         
        
   	}
   
}
