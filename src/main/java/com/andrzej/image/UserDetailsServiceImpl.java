package com.andrzej.image;

import com.andrzej.image.repo.UserAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserAppRepo userAppRepo;

    @Autowired
    public UserDetailsServiceImpl(UserAppRepo userAppRepo) {
        this.userAppRepo = userAppRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepo.findByUsername(username);
    }
}
