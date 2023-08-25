package com.zti.partpicker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents a Configuration entity and stores information about
 * each and every computer part in it as their respective ID value.
 * It consists of:
 * configuration id, CPU id, GPU id, memory id, motherboard id, storage id.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long account;

    private Long cpu;

    private Long gpu;

    private Long memory;

    private Long motherboard;

    private Long storage;

}
