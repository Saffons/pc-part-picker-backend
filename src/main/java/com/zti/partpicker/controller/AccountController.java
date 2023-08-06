package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.AccountNotFoundException;
import com.zti.partpicker.model.Account;
import com.zti.partpicker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth/account")
public class AccountController {

    @Autowired
    private final AccountRepository repository;

    AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<Account> all() {
        return repository.findAll();
    }

    @GetMapping("/hey")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!" + authentication.getAuthorities();
    }

    @PostMapping
    Account newAccount(@RequestBody Account newAccount) {
        PasswordEncoder enc = new BCryptPasswordEncoder();
        newAccount.setPassword(enc.encode(newAccount.getPassword()));
        newAccount.setRole("ROLE_user");
        return repository.save(newAccount);
    }

    @PostMapping("/login")
    void login(@RequestBody Account account) {
        PasswordEncoder enc = new BCryptPasswordEncoder();
        account.setPassword(enc.encode(account.getPassword()));
        Account acc = repository.getAccountByLogin(account.getLogin());

        if (account.getPassword() == acc.getPassword());
    }

    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}