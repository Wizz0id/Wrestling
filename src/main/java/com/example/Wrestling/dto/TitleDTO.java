package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleDTO {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String picture;
    private String promotionName;
    private String promotionPicture;
    private List<Long> wrestlersId;

    TitleDTO(long id, String name, byte[] promotionPicture){
        this.id = id;
        this.name = name;
        this.promotionPicture = Base64.getEncoder().encodeToString(promotionPicture);
    }
}
