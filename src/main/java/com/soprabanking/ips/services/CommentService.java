package com.soprabanking.ips.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprabanking.ips.daos.CommentDAO;
import com.soprabanking.ips.models.Comment;

@Service
public class CommentService 
{
	@Autowired
	private CommentDAO commentDao;
	
	public List<Comment> displayComments(Long proposalId)
	{
		try
		{
		return commentDao.fetchAllComments(proposalId);
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
			Comment addedComment =commentDao.createComment(comment);
		return addedComment;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	

}
