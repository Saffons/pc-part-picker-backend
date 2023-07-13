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
@Table(name = "gpu")
public class GPU extends ComputerPart {
    @Column(name = "cores")
    int memory;

    @Column(name = "speed")
    double speed;
}
