package com.zti.partpicker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents CPU entity and consists of following information:
 * number of its cores, clock speed (in MHz) and its socket.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cpu")
public class CPU extends ComputerPart {
    @Column(name = "cores")
    int cores;

    @Column(name = "speed")
    double speed;

    @Column(name = "socket")
    String socket;
}
