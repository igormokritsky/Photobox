package com.igormokritsky.Photobox.service;


import com.igormokritsky.Photobox.model.User;
import com.igormokritsky.Photobox.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);


}
