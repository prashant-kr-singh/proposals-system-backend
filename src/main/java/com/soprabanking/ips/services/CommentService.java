package com.soprabanking.ips.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.soprabanking.ips.daos.CommentDAO;
import com.soprabanking.ips.models.Comment;
import com.soprabanking.ips.utilities.JsonUtil;

@Service
public class CommentService 
{
	@Autowired
	private CommentDAO commentDao;
	
	public List<Comment> displayComments(String body)
	{
		try
		{
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			Long proposalId=Long.parseLong(jsonObj.get("id").asText());
			
			List<Comment> result= commentDao.fetchAllComments(proposalId);
		    return result;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public Comment addComment(Comment comment)
	{
		try
		{
			if(comment==null)
				throw new Exception("Unable to add comment");
		Comment addedComment =commentDao.createComment(comment);
		return addedComment;
		}
		catch(Exception e)
		{
		   return null;
		}
		
		
	}
	

}
