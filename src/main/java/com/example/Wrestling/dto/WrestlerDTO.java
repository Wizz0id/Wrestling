package com.example.Wrestling.dto;

import com.example.Wrestling.enumurate.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrestlerDTO {
    private long id;
    private String fio;
    private float height;
    private float weight;
    private byte[] picture;
    private Gender gender;
    private String trainer;
    private LocalDate startOfCareer;
    private boolean retired;
    private long promotionID;
    private List<Long> gimmicksIDs; // TODO убрать при ненадобности
    private List<Long> titlesIDs; // аналогично
    private List<Long> MatchesIDs; // аналогично
}
