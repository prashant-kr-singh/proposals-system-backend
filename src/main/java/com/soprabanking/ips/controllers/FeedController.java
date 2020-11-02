package com.soprabanking.ips.controllers;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.*;

import com.soprabanking.ips.services.ProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.models.Team;
import com.soprabanking.ips.models.User;
import com.soprabanking.ips.repositories.ProposalRepository;
import com.soprabanking.ips.repositories.UserRepository;
import com.soprabanking.ips.services.FeedService;

@CrossOrigin
@RestController
@RequestMapping("/feed")
public class FeedController {
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProposalRepository proposalRepository;

	@Autowired
	ProposalService proposalService;
	
	@GetMapping("/save")
	public User save() {
		
		User user1 = new User();
		user1.setName("Prashant Singh");
		user1.setEmail("pks@gmail.com");
		user1.setPassword("pks");
		user1.setCreationDate(new Date());
		
		Team team1 = new Team();
		team1.setName("team 1");
		user1.setTeam(team1);
		userRepository.save(user1);
		
		User user2 = new User();
		user2.setName("Japneet Singh");
		user2.setEmail("jps@gmail.com");
		user2.setPassword("jps");
		user2.setCreationDate(new Date());
		user2.setTeam(team1);
		userRepository.save(user2);
		
		User user3 = new User();
		user3.setName("Kartik Sachdeva");
		user3.setEmail("kts@gmail.com");
		user3.setPassword("kts");
		user3.setCreationDate(new Date());
		
		Team team2 = new Team();
		team2.setName("team 2");
		user3.setTeam(team2);
		userRepository.save(user3);
		
		User user4 = new User();
		user4.setName("Priyank Saini");
		user4.setEmail("pys@gmail.com");
		user4.setPassword("pys");
		user4.setCreationDate(new Date());
		user4.setTeam(team2);
		userRepository.save(user4);
		
		Set<Team> l1 = new HashSet<>();
		l1.add(team1);
		
		Set<Team> l2 = new HashSet<>();
		l2.add(team1);
		l2.add(team2);
		
		Proposal p1 = new Proposal();
		p1.setTitle("Title 1");
		p1.setDescription("Description 1");
		p1.setUpvotesCount((long)10);
		p1.setCreationDate(new Date());
		p1.setUser(user1);
		p1.setTeams(l1);
		proposalRepository.save(p1);
		
		Proposal p2 = new Proposal();
		p2.setTitle("Title 2");
		p2.setDescription("Description 2");
		p2.setUpvotesCount((long)2);
		p2.setCreationDate(new Date());
		p2.setUser(user2);
		p2.setTeams(l2);
		proposalRepository.save(p2);
		
		Proposal p3 = new Proposal();
		p3.setTitle("Title 3");
		p3.setDescription("Description 3");
		p3.setUpvotesCount((long)4);
		p3.setCreationDate(new Date());
		p3.setUser(user3);
		p3.setTeams(l1);
		proposalRepository.save(p3);
		
		Proposal p4 = new Proposal();
		p4.setTitle("Title 4");
		p4.setDescription("Description 4");
		p4.setUpvotesCount((long)8);
		p4.setCreationDate(new Date());
		p4.setUser(user4);
		p4.setTeams(l2);
		proposalRepository.save(p4);
		
		
		User u = userRepository.getOne((long) 1);
		
		
		return u;
	}
	
	@PostMapping(value = "/all", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Proposal>> getAllProposalFeed(
				@RequestBody String body){
		
		try {
			
			return new ResponseEntity<>(feedService.fetchAllProposals(body),
						HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<List<Proposal>>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@PostMapping(value = "/created", consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Proposal>> getUserProposalFeed(
				@RequestBody String body){
		
		try {
			return new ResponseEntity<>(feedService.fetchUserProposals(body),
						HttpStatus.OK);
		}
		catch(Exception ex) {
			return new ResponseEntity<List<Proposal>>(new ArrayList<>(), HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@PostMapping("/team")
	public ResponseEntity<Object> defaults(@RequestBody String body){
		Optional<List<Proposal>> optionalProposalDtoList= Optional.ofNullable(proposalService.getDefault(body));
		return optionalProposalDtoList.<ResponseEntity<Object>>map(proposals -> new ResponseEntity<>(proposals, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>("No data available for current team.", HttpStatus.NOT_FOUND));
	}


}
