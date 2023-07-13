package com.zti.partpicker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "storage")
public class Storage extends ComputerPart {
    @Column(name = "m2")
    boolean m2;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "speed")
    int speed;
}
