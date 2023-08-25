package com.zti.partpicker;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.zti.partpicker.controller.AccountController;
import com.zti.partpicker.model.Account;
import com.zti.partpicker.repository.AccountRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * This is a test class that checks some functionalities of AccountController class
 */
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    @WithMockUser(authorities = "ROLE_ADMIN")
    public void testAllAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1L, "user1", "hashedPassword", "ROLE_USER", "John", "Doe", "john@example.com", null));
        accounts.add(new Account(2L, "user2", "hashedPassword", "ROLE_USER", "Jane", "Doe", "jane@example.com", null));

        when(accountRepository.findAll()).thenReturn(accounts);

        mockMvc.perform(get("/api/auth/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(accounts.size()));
    }

    @Test
    @WithMockUser(authorities = "SCOPE_ROLE_USER")
    public void testGetAccountById() throws Exception {
        Long accountId = 1L;
        Account account = new Account(accountId, "user1", "hashedPassword", "ROLE_USER", "John", "Doe", "john@example.com", null);

        when(accountRepository.findById(accountId)).thenReturn(Optional.of(account));

        mockMvc.perform(get("/api/auth/account/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(accountId));
    }

    @Test
    @WithMockUser(authorities = "ROLE_USER")
    public void testHello() throws Exception {
        Authentication authentication = mock(Authentication.class);
        Collection grantedAuthorities = Lists.newArrayList(
                new SimpleGrantedAuthority("ROLE_USER"));
        when(authentication.getName()).thenReturn("user");
        when(authentication.getAuthorities()).thenReturn(grantedAuthorities);

        mockMvc.perform(get("/api/auth/account/hey").with(request -> {
                    request.setUserPrincipal(authentication);
                    return request;
                }))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, user![ROLE_USER]"));
    }
}