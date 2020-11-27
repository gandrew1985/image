package com.andrzej.image;

import com.andrzej.image.model.AppUser;
import com.andrzej.image.repo.UserAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserAppRepo userAppRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserAppRepo userAppRepo) {
        this.userDetailsService = userDetailsService;
        this.userAppRepo = userAppRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // creating user and save it into memory!!!
        //auth.inMemoryAuthentication().withUser(new User("Jan",
        // passwordEncoder().encode("Jan123"),
        // Collections.singleton(new SimpleGrantedAuthority("USER"))));

        //user from database
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/test").authenticated()
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser appUser = new AppUser("Jan", passwordEncoder().encode("Nowak"), "USER");
        userAppRepo.save(appUser);
    }
}
