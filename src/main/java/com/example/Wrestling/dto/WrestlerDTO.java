package com.example.Wrestling.dto;

import com.example.Wrestling.enumurate.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrestlerDTO {
    private long id;
    private String fio;
    private float height;
    private float weight;
    private String picture;
    private Gender gender;
    private String trainer;
    private LocalDate startOfCareer;
    private boolean retired;
    private String promotionPicture;
    private String promotionName;
}
