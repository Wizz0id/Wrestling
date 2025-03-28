package com.example.Wrestling.dto;

import com.example.Wrestling.enumurate.MatchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private long id;
    private String name;
    private MatchType type;
    private String url;
    private float professionalRating;
    private long eventId;
    private long winnerId;
}
