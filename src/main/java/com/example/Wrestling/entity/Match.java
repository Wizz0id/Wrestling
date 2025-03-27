package com.example.Wrestling.entity;

import com.example.Wrestling.enumurate.MatchType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "match", schema = "public")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private MatchType type;
    @Column
    private String url;
    @Column
    private float professionalRating;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Event event;

    @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MatchRenew> matchRenews;

    @ManyToMany(mappedBy = "matches")
    private List<Wrestler> wrestlers;
}
