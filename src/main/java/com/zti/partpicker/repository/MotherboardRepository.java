package com.zti.partpicker.repository;

import com.zti.partpicker.model.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface of type JpaRepository<Motherboard, Long> that allows to make
 * operations in database on Motherboard entities
 */
public interface MotherboardRepository extends JpaRepository<Motherboard, Long> {

}
