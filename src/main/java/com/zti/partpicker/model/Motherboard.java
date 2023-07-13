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
@Table(name = "motherboard")
public class Motherboard extends ComputerPart {
    @Column(name = "memoryType")
    String memoryType;

    @Column(name = "socket")
    String socket;

    @Column(name = "chipset")
    String chipset;

    @Column(name = "m2")
    boolean m2;
}
