package com.ascory.authservice.services;

import com.ascory.authservice.models.User;
import com.ascory.authservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(Long.parseLong(username))
                .orElseThrow(()->new UsernameNotFoundException("Invalid Username"));
    }
}
