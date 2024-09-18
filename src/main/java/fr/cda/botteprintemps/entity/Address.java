package fr.cda.botteprintemps.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import org.checkerframework.checker.units.qual.C;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private float longitude;

    @Column(nullable = false)
    private float latitude;

    private String more;

    @Column(nullable = false)
    private boolean isBilled;

    @ManyToOne
    private User user;

}