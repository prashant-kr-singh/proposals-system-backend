package com.soprabanking.ips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprabanking.ips.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findById(long id);
}
