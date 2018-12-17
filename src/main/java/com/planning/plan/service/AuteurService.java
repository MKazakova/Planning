package com.planning.plan.service;

import com.planning.plan.reposit.AuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuteurService implements UserDetailsService {
    @Autowired
    private AuteurRepository auteurRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return auteurRepository.findByLogin(s);
    }
}
