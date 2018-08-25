package com.survey.app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.survey.app.exception.ResourceNotFoundException;
import com.survey.app.model.User;
import com.survey.app.repository.UserRepository;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
         if(userOptional.isPresent())
                        new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail);

        return UserPrincipal.create(userOptional.get());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if(optional.isPresent())
            new ResourceNotFoundException("User", "id", id);

        return UserPrincipal.create(optional.get());
    }
}