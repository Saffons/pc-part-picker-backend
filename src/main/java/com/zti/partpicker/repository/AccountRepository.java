package com.zti.partpicker.repository;

import com.zti.partpicker.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT u FROM Account u WHERE u.login = :login")
    Account getAccountByLogin(@Param("login") String login);
}
