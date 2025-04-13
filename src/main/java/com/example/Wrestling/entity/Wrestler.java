package com.example.Wrestling.entity;

import com.example.Wrestling.enumurate.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "wrestler", schema = "public")
public class Wrestler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String fio;
    @Column
    private int height;
    @Column
    private int weight;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column
    private byte[] picture;
    @Column
    private String trainer;
    @Column
    private LocalDate startOfCareer;
    @Column
    private boolean retired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promo_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Promotion promotion;
    @OneToMany(mappedBy = "wrestler")
    @ToString.Exclude
    private List<Gimmick> gimmicks;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "champions",
            joinColumns = @JoinColumn(name = "wrestler_id"),
            inverseJoinColumns = @JoinColumn(name = "title_id")
    )
    @ToString.Exclude
    private List<Title> titles;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "participants",
            joinColumns = @JoinColumn(name = "wrestler_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    @ToString.Exclude
    private List<Match> matches;
}
