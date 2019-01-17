package com.burachenko.munichhotel.service;

import com.burachenko.munichhotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final Optional<UserEntity> user = userRepository.findUserByEmail(username);
//        if (!user.isPresent()) {
//            throw new UsernameNotFoundException(username);
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.get().getEmail(), user.get().getPassword(), true, true, true,
//                true, getAuthorities(RoleEnum.ADMIN));
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(
//            RoleEnum role) {
//        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
//        List<GrantedAuthority> authorities = Arrays.asList(simpleGrantedAuthority);
//        return authorities;
//    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        return userRepository.findUserByEmail(email)
                .map(user -> User.withUsername(email).passwordEncoder(passwordEncoder::encode)
                        .password(user.getPassword())
                        .authorities(user.getRole().toString())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("No user with email " + email));
    }
}
