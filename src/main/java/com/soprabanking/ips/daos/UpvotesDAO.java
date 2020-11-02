package com.soprabanking.ips.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.models.Upvotes;
import com.soprabanking.ips.repositories.ProposalRepository;
import com.soprabanking.ips.repositories.UpvotesRepository;

@Component
public class UpvotesDAO 
{
	@Autowired
	private UpvotesRepository upvotesRepository;
	
	private ProposalRepository proposalRepository;
	
	public Upvotes createUpvote(Upvotes upvote)
	{
		Proposal proposalUpvoted=upvote.getProposal();
		Long count=proposalUpvoted.getUpvotesCount();
		proposalUpvoted.setUpvotesCount(count+1);
		proposalRepository.save(proposalUpvoted);
		return upvotesRepository.save(upvote);
	}
	public List<Upvotes> fetchAllUpvotes(Long proposalId)
	{
		return upvotesRepository.findAllByProposalId(proposalId);
	}
	public void deleteUpvote(Upvotes upvote) {
		// TODO Auto-generated method stub
		Proposal proposalUpvoted=upvote.getProposal();
		Long count=proposalUpvoted.getUpvotesCount();
		proposalUpvoted.setUpvotesCount(count-1);
		proposalRepository.save(proposalUpvoted);
		upvotesRepository.delete(upvote);
		
	}
	
}
