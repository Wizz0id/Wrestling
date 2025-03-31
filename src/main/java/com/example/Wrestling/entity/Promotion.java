package com.example.Wrestling.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;


@Data
@Entity
@Table(name = "promotion", schema = "public")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(unique = true)
    private String name;
    @Column
    private String fioOfCeo;
    @Column
    private byte[] picture;

    @OneToMany(mappedBy = "promotion")
    @ToString.Exclude
    private List<Wrestler> wrestlers;

    @OneToMany(mappedBy = "promotion")
    @ToString.Exclude
    private List<Event> events;

    @OneToMany(mappedBy = "promotion")
    @ToString.Exclude
    private List<Title> titles;
}
