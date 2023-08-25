package com.zti.partpicker.repository;

import com.zti.partpicker.model.CPU;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface of type JpaRepository<CPU, Long> that allows to make
 * operations in database on CPU entities
 */
public interface CPURepository extends JpaRepository<CPU, Long> {

}
