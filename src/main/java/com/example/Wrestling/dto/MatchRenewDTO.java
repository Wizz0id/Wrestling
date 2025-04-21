package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchRenewDTO {
    private long id;
    private String username;
    private double rating;
    private String renew;
}
