package com.zti.partpicker.repository;

import com.zti.partpicker.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    List<Configuration> findAllByCpu(Long cpu);
    List<Configuration> findAllByGpu(Long gpu);
    List<Configuration> findAllByMemory(Long memory);
    List<Configuration> findAllByMotherboard(Long motherboard);
    List<Configuration> findAllByStorage(Long storage);
    List<Configuration> findAllByAccount(Long accountId);
}
