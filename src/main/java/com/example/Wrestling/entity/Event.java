package com.example.Wrestling.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name="event", schema = "public")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String name;
    @Column
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "promo_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Promotion promotion;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<EventRenew> eventRenews;

    @OneToMany(mappedBy = "event")
    @ToString.Exclude
    List<Match> matches;
}
