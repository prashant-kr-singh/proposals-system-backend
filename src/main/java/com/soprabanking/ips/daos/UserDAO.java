package com.soprabanking.ips.daos;

import com.soprabanking.ips.models.Team;
import com.soprabanking.ips.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {
    @Autowired
    UserRepository userRepository;
    public Team getTeam(long id){
        return userRepository.getById(id);
    }
}
