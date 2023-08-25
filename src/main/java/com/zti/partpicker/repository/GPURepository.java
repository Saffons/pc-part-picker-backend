package com.zti.partpicker.repository;

import com.zti.partpicker.model.GPU;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface of type JpaRepository<GPU, Long> that allows to make
 * operations in database on GPU entities
 */
public interface GPURepository extends JpaRepository<GPU, Long> {

}
