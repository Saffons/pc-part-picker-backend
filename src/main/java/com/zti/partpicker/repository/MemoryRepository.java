package com.zti.partpicker.repository;

import com.zti.partpicker.model.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface of type JpaRepository<Memory, Long> that allows to make
 * operations in database on Memory entities
 */
public interface MemoryRepository extends JpaRepository<Memory, Long> {

}
