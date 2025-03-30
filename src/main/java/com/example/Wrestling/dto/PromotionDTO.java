package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    private long id;
    private String name;
    private String fioOfCeo;
    private String picture;
}
