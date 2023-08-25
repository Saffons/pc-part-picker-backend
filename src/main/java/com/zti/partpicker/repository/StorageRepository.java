package com.zti.partpicker.repository;

import com.zti.partpicker.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface of type JpaRepository<Storage, Long> that allows to make
 * operations in database on Storage entities
 */
public interface StorageRepository extends JpaRepository<Storage, Long> {

}
