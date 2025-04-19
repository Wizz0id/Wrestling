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
    private MatchType type;
    private String url;
    private double professionalRating;
    private double peoplesRating;
    private long eventId;
    private long winnerId;
    private List<Long> wrestlersId;

    MatchDTO(long id, String name, String type, double professionalRating, double peoplesRating) {
        this.id = id;
        this.name = name;
        this.type = MatchType.valueOf(type);
        this.professionalRating = professionalRating;
        this.peoplesRating = peoplesRating;
    }
}
