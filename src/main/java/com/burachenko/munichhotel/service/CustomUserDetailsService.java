package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.enumeration.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final UserDto userDto = userService.findUserByEmail(email);
        if (userDto == null){
            throw new UsernameNotFoundException(email);
        }
        return new User(userDto.getEmail(),
                userDto.getPassword(),
                userDto.getBlocking() > 0,
                true,
                true,
                true,
                 getAuthorities(userDto.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(final UserRole role){
        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        return Arrays.<GrantedAuthority>asList(simpleGrantedAuthority);
    }
}
