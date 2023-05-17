package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "cpu")
public class CPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "manufacturer")
    String manufacturer;

    @Column(name = "cores")
    int cores;

    @Column(name = "speed")
    int speed;

    @Column(name = "socket")
    String socket;

    @Column(name = "price")
    double price;

    public CPU() {

    }

    public CPU(Long id) {
        this.id = id;
    }
}
