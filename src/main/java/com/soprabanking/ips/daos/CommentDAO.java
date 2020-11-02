package com.soprabanking.ips.daos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.soprabanking.ips.models.Comment;
import com.soprabanking.ips.repositories.CommentRepository;

@Component
public class CommentDAO {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public List<Comment> fetchAllComments(Long proposalId)
	{
		return commentRepository.findAllByProposalId(proposalId);
	}
	
	public Comment createComment(Comment com)
	{
		com.setCreationDate(new Date());
		Comment saved=commentRepository.save(com);
		return saved;
	}

}
