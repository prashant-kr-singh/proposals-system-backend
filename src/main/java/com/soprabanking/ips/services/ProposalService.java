package com.soprabanking.ips.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.soprabanking.ips.daos.ProposalDAO;
import com.soprabanking.ips.daos.TeamDAO;
import com.soprabanking.ips.daos.UserDAO;
import com.soprabanking.ips.models.*;
import com.soprabanking.ips.repositories.CommentRepository;
import com.soprabanking.ips.repositories.TeamRepository;
import com.soprabanking.ips.utilities.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProposalService {
    @Autowired
    ProposalDAO proposalDao;

   /* @Autowired
    TeamRepository teamRepository;*/

    @Autowired
    UserDAO userDAO;

    public List<Proposal> getDefault(String body) {
        try {
            LocalDate localDate = LocalDate.now().minusMonths(1);
            JsonNode jsonObj = JsonUtil.stringToJson(body);
            int page = Integer.parseInt(jsonObj.get("page").asText());
            int size = Integer.parseInt(jsonObj.get("size").asText());
            Long userId = Long.parseLong(jsonObj.get("userId").asText());
            Team team = userDAO.getTeam(userId);
            Date startDate = Date.from(localDate.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDate = Date.from(localDate.with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
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
