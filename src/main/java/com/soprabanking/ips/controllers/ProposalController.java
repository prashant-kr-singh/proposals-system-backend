package com.soprabanking.ips.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.services.ProposalService;

@RestController
@CrossOrigin
@RequestMapping("/proposal")
public class ProposalController {
	
	@Autowired
	private ProposalService proposalService;

	@PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProposal(@RequestBody String body){
		
		try {
			return new ResponseEntity<String>("Created Proposal '"+proposalService.saveProposal(body).getTitle()+"'",HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<String>("Proposal not saved.", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
