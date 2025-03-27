package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    public long id;
    public String name;
    public LocalDate date;
    private String promotion;
    private List<Long> eventRenewIDs; // TODO убрать при ненадобности
    private List<Long> matchIDs; // аналогично
}
