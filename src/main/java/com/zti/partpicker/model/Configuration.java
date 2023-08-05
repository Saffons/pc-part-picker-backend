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
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Account account;

    @OneToOne
    @MapsId
    private CPU cpu;

    @OneToOne
    @MapsId
    private GPU gpu;

    @OneToOne
    @MapsId
    private Memory memory;

    @OneToOne
    @MapsId
    private Motherboard motherboard;

    @OneToOne
    @MapsId
    private Storage storage;

}
