package com.soprabanking.ips.repositories;

import java.util.Date;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import com.soprabanking.ips.models.Proposal;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
		
	Slice<Proposal> findSliceBy(Pageable pageable);
	
	Slice<Proposal> findAllByCreationDateBetween(Date startDate, Date endDate, Pageable pageable);
	

}
