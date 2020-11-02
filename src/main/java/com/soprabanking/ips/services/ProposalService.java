package com.soprabanking.ips.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.soprabanking.ips.daos.ProposalDAO;
import com.soprabanking.ips.daos.TeamDAO;
import com.soprabanking.ips.models.Proposal;
import com.soprabanking.ips.models.Team;
import com.soprabanking.ips.utilities.DateUtil;
import com.soprabanking.ips.utilities.JsonUtil;

@Service
public class ProposalService {
    @Autowired
    ProposalDAO proposalDao;

   /* @Autowired
    TeamRepository teamRepository;*/

    @Autowired
    TeamDAO teamDAO;

    public List<Proposal> getDefault(String body) {
        try {
            
        	JsonNode jsonObj = JsonUtil.stringToJson(body);
			
			Date startDate = DateUtil.stringToISTDate(jsonObj.get("startDate").asText());
			Date endDate = DateUtil.stringToISTDate(jsonObj.get("endDate").asText());
			int page = Integer.parseInt(jsonObj.get("page").asText());
			int size = Integer.parseInt(jsonObj.get("size").asText());
			Long teamId = Long.parseLong(jsonObj.get("teamId").asText());
			
			Team team = teamDAO.getTeam(teamId);
        	List<Proposal> proposals = proposalDao.getDefault(team, startDate, endDate, PageRequest.of(page, size, Sort.Direction.DESC, "upvotesCount"));

            return !proposals.isEmpty() ? proposals : null;

        }
        catch (Exception e){
            return null;
        }
    }

    /*public List<Proposal> getDefault(Team  team, int page, Date startDate, Date endDate) {
        System.out.println(startDate);
        System.out.println(endDate);
        return getProposalDtos(team, page, startDate, endDate);
    }*/

}
