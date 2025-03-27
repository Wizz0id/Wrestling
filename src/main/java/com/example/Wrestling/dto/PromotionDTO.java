package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
    private long id;
    private String name;
    private String fioOfCeo;
    private List<Long> wrestlersIDs; // TODO убрать при ненадобности
    private List<Long> eventsIDs;  // аналогично
    private List<Long> titlesIDs; // аналогично
}
