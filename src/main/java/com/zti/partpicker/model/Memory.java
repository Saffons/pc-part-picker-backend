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
@Table(name = "memory")
public class Memory extends ComputerPart {
    @Column(name = "memoryType")
    String memoryType;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "speed")
    int speed;
}
