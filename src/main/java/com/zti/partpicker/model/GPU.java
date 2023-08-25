package com.zti.partpicker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents GPU entity and consists of following information:
 * capacity of its memory (in MB) and its clock speed (in MHz).
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "gpu")
public class GPU extends ComputerPart {
    @Column(name = "cores")
    int memory;

    @Column(name = "speed")
    double speed;
}
