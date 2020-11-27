package com.andrzej.image.repo;

import com.andrzej.image.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
