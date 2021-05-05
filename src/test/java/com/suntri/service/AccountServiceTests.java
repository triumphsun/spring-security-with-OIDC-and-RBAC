package com.suntri.service;

import com.suntri.domain.AccountEntity;
import com.suntri.domain.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTests {

    @Mock
    private AccountRepository mockedAccountRepository;

    @Autowired
    @InjectMocks
    private AccountService accountService;

    @Test
    public void whenEmailDoesExist_thenReturnRelevantResult(){
        String email = "test@gmail.com";
        AccountEntity mockedEntity = Mockito.mock(AccountEntity.class);
        when(mockedEntity.getEmail()).thenReturn(email);
        when(mockedEntity.getCreatedAt()).thenReturn(new Date());
        when(mockedEntity.getRoles()).thenReturn(Collections.emptySet());
        when(mockedAccountRepository.findByEmail(email)).thenReturn(Optional.of(mockedEntity));
        Optional<Account> optAccount = this.accountService.findAccountByEmail(email);

        Assertions.assertTrue(optAccount.isPresent());
        Assertions.assertEquals(email, optAccount.get().getEmail());
    }

    @Test
    public void whenEmailDoesNotExist_thenReturnOptionalEmpty(){
        String email = "test@gmail.com";
        when(mockedAccountRepository.findByEmail(email)).thenReturn(Optional.empty());

        Assertions.assertEquals(Optional.empty(), this.accountService.findAccountByEmail(email));
    }
}
