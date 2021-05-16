package com.suntri.web;

import com.suntri.service.Account;
import com.suntri.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z");

    @RolesAllowed("ROLE_ADMIN")
    @RequestMapping(method = {RequestMethod.GET}, value = "/inspect/{email}")
    public ModelAndView inspect(@PathVariable("email") String email){
        ModelAndView mnv = new ModelAndView();
        Optional<Account> optAccount = this.accountService.findAccountByEmail(email);
        if(optAccount.isPresent()){
            mnv.setViewName("views/inspect-user");
            mnv.addObject("account", this.map(optAccount.get()));
        } else {
            mnv.setViewName("views/inspect-user-not-found");
        }
        return mnv;
    }

    private AccountDto map(Account account){
        if(account==null) throw new IllegalArgumentException("account cannot be null.");

        AccountDto dto = new AccountDto();
        dto.setEmail(account.getEmail());
        dto.setCreatedAt(this.formatter.format(account.getCreatedAt()));
        for(String role: account.getRoles()){
            dto.addRoles(role);
        }
        return dto;
    }
}
