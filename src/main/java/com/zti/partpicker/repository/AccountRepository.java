package com.zti.partpicker.repository;

import com.zti.partpicker.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Interface of type JpaRepository<Account, Long> that allows to make
 * operations in database on Account entities
 */
@Component
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * @param login wanted username
     * @return Account object that has username equal to login
     */
    Account findAccountByLogin(String login);
}
