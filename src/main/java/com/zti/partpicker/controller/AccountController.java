package com.zti.partpicker.controller;

import java.util.List;

import com.zti.partpicker.exception.AccountNotFoundException;
import com.zti.partpicker.model.Account;
import com.zti.partpicker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    Account newAccount(@RequestBody Account newAccount) {
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