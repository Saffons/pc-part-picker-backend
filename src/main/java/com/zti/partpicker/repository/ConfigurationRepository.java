package com.zti.partpicker.repository;

import com.zti.partpicker.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}
