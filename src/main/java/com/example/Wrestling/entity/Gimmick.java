package com.example.Wrestling.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="gimmick", schema = "public")
public class Gimmick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wrestler_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Wrestler wrestler;
}
