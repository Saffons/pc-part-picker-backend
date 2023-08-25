package com.zti.partpicker.repository;

import com.zti.partpicker.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface of type JpaRepository<Configuration, Long> that allows to make
 * operations in database on Configuration entities
 */
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    /**
     * @param cpu wanted CPU's id
     * @return list of all configurations that consist of CPU with id equal to id
     */
    List<Configuration> findAllByCpu(Long cpu);
    /**
     * @param gpu wanted GPU's id
     * @return list of all configurations that consist of GPU with id equal to id
     */
    List<Configuration> findAllByGpu(Long gpu);
    /**
     * @param memory wanted memory's id
     * @return list of all configurations that consist of memory with id equal to id
     */
    List<Configuration> findAllByMemory(Long memory);
    /**
     * @param motherboard wanted motherboard's id
     * @return list of all configurations that consist of motherboard with id equal to id
     */
    List<Configuration> findAllByMotherboard(Long motherboard);
    /**
     * @param storage wanted storage's id
     * @return list of all configurations that consist of storage with id equal to id
     */
    List<Configuration> findAllByStorage(Long storage);
    /**
     * @param accountId wanted account's id
     * @return list of all configurations that user of with id equal to id created
     */
    List<Configuration> findAllByAccount(Long accountId);
}
