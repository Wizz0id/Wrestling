package com.example.Wrestling.dto;

import com.example.Wrestling.entity.Match;
import com.example.Wrestling.mapper.MatchMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchWithRatingDTO {
    private MatchDTO match;
    private double rating;

    MatchWithRatingDTO(Match match, double rating) {
        this.match = MatchMapper.ToDTO(match);
        this.rating = Math.round(rating * 100) / 100.0;
    }
}
