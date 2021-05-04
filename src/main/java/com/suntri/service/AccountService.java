package com.suntri.service;

import com.suntri.domain.AccountEntity;
import com.suntri.domain.AccountRepository;
import com.suntri.domain.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Account> findAccountByEmail(String email){
        Optional<AccountEntity> optAccountEntity = this.accountRepository.findByEmail(email);
        if(optAccountEntity.isPresent()){
            return Optional.of(this.map(optAccountEntity.get()));
        } else {
            return Optional.empty();
        }
    }

    private Account map(AccountEntity accountEntity){
        AccountImpl account = new AccountImpl();
        account.setEmail(accountEntity.getEmail());
        account.setCreatedAt(accountEntity.getCreatedAt());
        for(RoleEntity role: accountEntity.getRoles()){
            account.addRoles(role.getName());
        }
        return account;
    }

}
