package com.zti.partpicker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents Motherboard entity and consists of following information:
 * type of memory slots, motherboard's CPU socket, chipset and information if
 * there are any M2 slots on the board.
 */
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
