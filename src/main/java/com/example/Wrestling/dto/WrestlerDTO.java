package com.example.Wrestling.dto;

import com.example.Wrestling.enumurate.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Base64;


@Data
@NoArgsConstructor
public class WrestlerDTO {
    private long id;
    private String fio;
    private int height;
    private int weight;
    private String picture;
    private String gender;
    private String trainer;
    private LocalDate startOfCareer;
    private boolean retired;
    private long promotionId;
    private long countOfMatches;

    WrestlerDTO(long id, String fio, int height, int weight, byte[] picture,
                Gender gender, String trainer, LocalDate startOfCareer,
                boolean retired, long promotionId, long countOfMatches) {
        this.id = id;
        this.fio = fio;
        this.weight = weight;
        this.height = height;
        this.picture = Base64.getEncoder().encodeToString(picture);
        this.gender = gender.getDescription();
        this.trainer = trainer;
        this.startOfCareer = startOfCareer;
        this.retired = retired;
        this.promotionId = promotionId;
        this.countOfMatches = countOfMatches;
    }
}
