package com.zti.partpicker.repository;

import com.zti.partpicker.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
