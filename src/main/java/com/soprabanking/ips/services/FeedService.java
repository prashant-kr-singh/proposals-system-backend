package com.soprabanking.ips.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprabanking.ips.daos.ProposalDAO;
import com.soprabanking.ips.models.Proposal;

@Service
public class FeedService {

	@Autowired
	private ProposalDAO proposalDAO;
	
	public List<Proposal> fetchAllProposals(String body) throws Exception {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode obj = mapper.readTree(body);
			
			String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
			Date startDate = new SimpleDateFormat(dateFormat).parse(obj.get("startDate").asText());
			Date endDate = new SimpleDateFormat(dateFormat).parse(obj.get("endDate").asText());
			int page = Integer.parseInt(obj.get("page").asText());
			int size = Integer.parseInt(obj.get("size").asText());
			
			
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
