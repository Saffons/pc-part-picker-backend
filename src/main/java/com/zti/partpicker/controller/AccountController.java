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

/**
 * Controller class responsible for handling account-related operations and endpoints.
 */
@CrossOrigin(origins = "https://kaktus-react-spring.netlify.app")
@RestController
@RequestMapping("/api/auth/account")
public class AccountController {

    @Autowired
    private final AccountRepository repository;

    /**
     * Constructor for the AccountController class.
     *
     * @param repository An instance of AccountRepository for interacting with the account data.
     */
    AccountController(AccountRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a list of all accounts.
     *
     * @return A list of Account objects.
     */
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'SCOPE_ROLE_ADMIN')")
    List<Account> all() {
        return repository.findAll();
    }

    /**
     * Returns a friendly greeting along with user authentication details.
     *
     * @param authentication The Authentication object representing the current user's authentication.
     * @return A greeting string along with the user's name and authorities.
     */
    @GetMapping("/hey")
    public String hello(Authentication authentication) {
        return "Hello, " + authentication.getName() + "!" + authentication.getAuthorities();
    }

    /**
     * Creates a new user account.
     *
     * @param newAccount The Account object containing the details of the new account.
     * @return The saved Account object with encrypted password and default role.
     */
    @PostMapping
    Account newAccount(@RequestBody Account newAccount) {
        PasswordEncoder enc = new BCryptPasswordEncoder();
        newAccount.setPassword(enc.encode(newAccount.getPassword()));
        newAccount.setRole("ROLE_USER");
        return repository.save(newAccount);
    }

    /**
     * Retrieves details of a single account by its ID.
     *
     * @param id The ID of the account to retrieve.
     * @return The Account object corresponding to the provided ID.
     * @throws AccountNotFoundException If no account with the given ID is found.
     */
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_USER')")
    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    /**
     * Deletes an account by its ID.
     *
     * @param id The ID of the account to delete.
     */
    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}