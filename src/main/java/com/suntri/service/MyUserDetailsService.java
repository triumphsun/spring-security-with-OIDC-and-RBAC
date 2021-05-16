package com.suntri.service;

import com.suntri.domain.AccountEntity;
import com.suntri.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AccountEntity> optEntity = this.accountRepository.findByEmail(username);
        if(optEntity.isPresent()){
            AccountEntity entity = optEntity.get();
            return User
                    .builder()
                    .username(username)
                    .password(entity.getPassword())
                    .authorities("Admin")
                    .build();
        }
        throw new UsernameNotFoundException(String.format("Email: %s not found.", username));
    }

}
