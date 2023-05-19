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
@Table(name = "cpu")
public class CPU extends ComputerPart {
    @Column(name = "cores")
    int cores;

    @Column(name = "speed")
    int speed;

    @Column(name = "socket")
    String socket;
}
