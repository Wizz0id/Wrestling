package com.example.Wrestling.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="gimmick", schema = "public")
public class Gimmick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name; // TODO хранить промежуток?
    @ManyToOne
    @JoinColumn(name = "wrestler_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Wrestler wrestler;
}
