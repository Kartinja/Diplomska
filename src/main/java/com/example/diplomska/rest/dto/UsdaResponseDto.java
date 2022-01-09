package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UsdaResponseDto {
    int totalHits;
    int currentPage;
    int totalPages;
    List<Integer> pageList;
    Object foodSearchCriteria;
    List<FoodDto> foods;
}
