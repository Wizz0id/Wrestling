package com.example.Wrestling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrestlerResponseDTO {
    private PromotionDTO promotion;
    private WrestlerDTO wrestler;
}
