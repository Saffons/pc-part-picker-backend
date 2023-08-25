package com.zti.partpicker.model;

import com.zti.partpicker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This class implements UserDetailsService interface that is being used in Spring
 * Security for authentication and endpoint security purposes.
 */
public class AccountDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository userRepository;

    /**
     * @param username
     * @return AccountDetails of user with login equal to username
     * @throws UsernameNotFoundException When user with login equal to username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = userRepository.findAccountByLogin(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new AccountDetails(user);
    }

}