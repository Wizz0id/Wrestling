package com.example.Wrestling.dto;

import com.example.Wrestling.enumurate.MatchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private long id;
    private String name;
    private String type;
    private String url;
    private double professionalRating;
    private double peoplesRating;
    private long eventId;
    private long winnerId;
    private List<Long> wrestlersId;

    MatchDTO(long id, String name, MatchType type, double professionalRating, double peoplesRating, long winnerId) {
        this.id = id;
        this.name = name;
        this.type = type.getName();
        this.professionalRating = Math.round(professionalRating * 100) / 100.0;
        this.peoplesRating = Math.round(peoplesRating * 100) / 100.0;
        this.winnerId = winnerId;
    }
}
