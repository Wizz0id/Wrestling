package com.example.Wrestling.entity;

import com.example.Wrestling.enumurate.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "wrestler", schema = "public")
public class Wrestler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String fio;
    @Column
    private float height;
    @Column
    private float weight;
    @Column
    private Gender gender;
    @Column
    private byte[] picture;
    @Column
    private String trainer;
    @Column
    private LocalDate startOfCareer;
    @Column
    private boolean retired;
    @ManyToOne
    @JoinColumn(name = "promo_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Promotion promotion;
    @OneToMany(mappedBy = "wrestler", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gimmick> gimmicks;

    @OneToMany(mappedBy = "wrestler", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Title> titles;

    @ManyToMany
    @JoinTable(
            name = "participants",
            joinColumns = @JoinColumn(name = "wrestler_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches;
}
