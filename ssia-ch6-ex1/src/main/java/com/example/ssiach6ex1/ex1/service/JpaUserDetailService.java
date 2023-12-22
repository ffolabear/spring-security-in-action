package com.example.ssiach6ex1.ex1.service;

import com.example.ssiach6ex1.ex1.entities.User;
import com.example.ssiach6ex1.ex1.model.CustomUserDetails;
import com.example.ssiach6ex1.ex1.repositories.UserRepository;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> supplier = () -> new UsernameNotFoundException(
                "Problem during authentication!");
        User user = userRepository.findUserByUsername(username).orElseThrow(supplier);
        return new CustomUserDetails(user);
    }
}
