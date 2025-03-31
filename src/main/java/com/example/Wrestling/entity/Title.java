package com.example.Wrestling.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "title", schema = "public")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String name;
    @Column
    private LocalDate start_date;
    @Column
    private LocalDate end_date;
    @Column
    private byte[] picture;
    @ManyToOne
    @JoinColumn(name = "promo_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Promotion promotion;
    @ManyToOne
    @JoinColumn(name = "wrestler_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    @ToString.Exclude
    private Wrestler wrestler; // TODO скорее всего надо изменить на ManyToMany если я хочу хранить историю передачи титула
}
