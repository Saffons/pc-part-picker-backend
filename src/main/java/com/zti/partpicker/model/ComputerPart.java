package com.zti.partpicker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract ComputerPart class, that consists of every piece of information
 * that every computer part has, such as:
 * part id, name, manufacturer and price (zł).
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public abstract class ComputerPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @PrimaryKeyJoinColumn(name="id")
    long id;

    @Column(name = "name")
    String name;

    @Column(name = "manufacturer")
    String manufacturer;

    @Column(name = "price")
    double price;
}
