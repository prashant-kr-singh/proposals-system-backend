package com.soprabanking.ips.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.soprabanking.ips.daos.ProposalDAO;
import com.soprabanking.ips.models.Proposal;

@Service
public class FeedService {

	@Autowired
	private ProposalDAO proposalDAO;
	
	public List<Proposal> fetchAllProposals(Date startDate, Date endDate, int page, int size) throws Exception {
		
		try {
			if(startDate.after(endDate))
				throw new Exception();
			
			Pageable pageable = PageRequest.of(page, size);
			Slice<Proposal> result = proposalDAO.fetchAllProposals(startDate, endDate, pageable);
			return result.getContent();
		}
		catch(Exception ex) {
			throw new Exception();
		}
	}
}
