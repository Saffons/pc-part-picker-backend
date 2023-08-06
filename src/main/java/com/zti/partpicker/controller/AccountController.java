package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.AccountNotFoundException;
import com.zti.partpicker.model.Account;
import com.zti.partpicker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
        newAccount.setRole("ROLE_USER");
        return repository.save(newAccount);
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