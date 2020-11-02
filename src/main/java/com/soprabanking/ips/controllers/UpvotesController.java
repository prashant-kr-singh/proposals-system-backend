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
    
    @PostMapping("/newupvote")
    public ResponseEntity<Upvotes> upvoteProposal(@RequestParam Upvotes upvote)
    {
    	return new ResponseEntity<Upvotes>(upvotesService.upvoteProposal(upvote),HttpStatus.OK);
    }
    {
    	
    }
}
