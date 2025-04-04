package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleDTO {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String picture;
    private long promotionID;
    private long wrestlerID; // TODO Изменить DTO при наличии истории титула
}
