package com.example.Wrestling.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match_renew", schema = "public")
public class MatchRenew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column
    private String username;
    @Column
    private double rating;
    @Column
    private String renew;
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Match match;
}
