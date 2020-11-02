package com.soprabanking.ips.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprabanking.ips.daos.UpvotesDAO;
import com.soprabanking.ips.models.Upvotes;

@Service
public class UpvotesService 
{
	@Autowired
	private UpvotesDAO upvotesDao;
	
	public Upvotes upvoteProposal(Upvotes upvote)
	{
		try
		{
			if(upvote==null)
				throw new Exception("Unable to upvote");
			
		Upvotes upvoted=upvotesDao.createUpvote(upvote);
		return upvoted;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public void reverseUpvote(Upvotes upvote)
	{
			upvotesDao.deleteUpvote(upvote);
		
	}
	
	public List<Upvotes> listUpvotes(Long proposalId)
	{
		try {
		return upvotesDao.fetchAllUpvotes(proposalId);
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	

}
