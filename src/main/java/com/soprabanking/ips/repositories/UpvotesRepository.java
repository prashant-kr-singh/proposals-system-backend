package com.soprabanking.ips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprabanking.ips.models.Upvotes;

public interface UpvotesRepository extends JpaRepository<Upvotes, Long> {

}
