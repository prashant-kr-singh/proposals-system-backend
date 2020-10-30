package com.soprabanking.ips.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.models.Team;
import com.soprabanking.ips.models.User;
import com.soprabanking.ips.repositories.TeamRepository;
import com.soprabanking.ips.repositories.UserRepository;
import com.soprabanking.ips.services.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {
	
	@Autowired
	private FeedService feedService;
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private UserRepository userRepository;
	
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
		
		User u = userRepository.getOne((long) 1);
		
		
		return u;
	}
	
	
	//[ "2020-10-15", "2020-10-29"]
	@PostMapping("/all")
	public ResponseEntity<List<Proposal>> getAllProposalFeed(
				@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
				@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
				@RequestParam int page,
				@RequestParam int size){
		
		System.out.println("d " + startDate);
		
		return new ResponseEntity<>(feedService.fetchAllProposals(startDate, endDate, page, size),
						HttpStatus.OK);
	}
}
