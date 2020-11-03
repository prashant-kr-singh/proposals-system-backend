package com.soprabanking.ips.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.soprabanking.ips.daos.ProposalDAO;
import com.soprabanking.ips.daos.UpvotesDAO;
import com.soprabanking.ips.daos.UserDAO;
import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.models.Upvotes;
import com.soprabanking.ips.models.User;
import com.soprabanking.ips.utilities.JsonUtil;

@Service
public class UpvotesService 
{
	@Autowired
	private UpvotesDAO upvotesDao;
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private ProposalDAO proposalDao;
	
	public Upvotes upvoteProposal(String body) throws Exception
	{
		try
		{
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			Long pid=Long.parseLong(jsonObj.get("id").asText());
			Long uid=Long.parseLong(jsonObj.get("userId").asText());
			User user=userDao.getById(uid);
			Proposal proposal=proposalDao.getById(pid);
			Upvotes upvote=new Upvotes();
			
			upvote.setProposal(proposal);
			upvote.setUser(user);
			
			proposal.setUpvotesCount(proposal.getUpvotesCount()+1);
			proposalDao.saveProposal(proposal);
			
		    Upvotes upvoted=upvotesDao.createUpvote(upvote);
		    return upvoted;
		}
		catch(Exception ex)
		{
			throw new Exception();
		}
	}
	
	public void reverseUpvote(String body)
	{
		try
		{
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			Long pid = Long.parseLong(jsonObj.get("id").asText());
			Long uid = Long.parseLong(jsonObj.get("userId").asText());
			Upvotes upvote = upvotesDao.getUpvoteforUserIdAndProposalId(uid, pid);
			Proposal proposal = proposalDao.getById(pid);
			proposal.setUpvotesCount(proposal.getUpvotesCount()-1);
			proposalDao.saveProposal(proposal);
			
		    upvotesDao.deleteUpvote(upvote);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*public List<Upvotes> listUpvotes(Long proposalId)
	{
		try {
		return upvotesDao.fetchAllUpvotes(proposalId);
		}
		catch(Exception ex)
		{
			return null;
		}
	}*/
	
	public boolean hasUpvoted(String body) throws Exception
	{
		try
		{
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			Long pid=Long.parseLong(jsonObj.get("id").asText());
			Long uid=Long.parseLong(jsonObj.get("userId").asText());
			Upvotes upvote=upvotesDao.getUpvoteforUserIdAndProposalId(uid, pid);
			if(upvote!=null)
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		}
		catch(Exception e)
		{
			throw new Exception();
		}
	}
	

}
