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
	
	public List<Proposal> fetchAllProposals(Date startDate, Date endDate, int page, int size) {
		
		try {

			JsonNode jsonObj = JsonUtil.stringToJson(body);
			
			Date startDate = DateUtil.stringToISTDate(jsonObj.get("startDate").asText());
			Date endDate = DateUtil.stringToISTDate(jsonObj.get("endDate").asText());
			int page = Integer.parseInt(jsonObj.get("page").asText());
			int size = Integer.parseInt(jsonObj.get("size").asText());
			
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

	public List<Proposal> fetchUserProposals(String body) throws Exception {

		try {
			JsonNode jsonObj = JsonUtil.stringToJson(body);
			
			Date startDate = DateUtil.stringToISTDate(jsonObj.get("startDate").asText());
			Date endDate = DateUtil.stringToISTDate(jsonObj.get("endDate").asText());
			int page = Integer.parseInt(jsonObj.get("page").asText());
			int size = Integer.parseInt(jsonObj.get("size").asText());
			Long userId = Long.parseLong(jsonObj.get("userId").asText());

			System.out.println(startDate);
			System.out.println(endDate);
			System.out.println(page);
			System.out.println(userId);
			System.out.println(size);
			
			if(startDate.after(endDate))
				throw new Exception();
			
			Pageable pageable = PageRequest.of(page, size);
			Slice<Proposal> result = proposalDAO.fetchUserProposals(userId, startDate, endDate, pageable);
			
			return result.getContent();
			
			
		}
		catch(Exception ex) {
			throw new Exception();

		}
	}
}
