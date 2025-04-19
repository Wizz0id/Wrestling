package com.example.Wrestling.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;

@Data
@NoArgsConstructor
public class EventDTO {
    public long id;
    public String name;
    public LocalDate date;
    private String promotionName;
    private String promotionPicture;

    EventDTO(long id, String name, Date date, String promotionName, byte[] promotionPicture) {
        this.id = id;
        this.name = name;
        this.date = date.toLocalDate();
        this.promotionName = promotionName;
        this.promotionPicture = Base64.getEncoder().encodeToString(promotionPicture);
    }
}
