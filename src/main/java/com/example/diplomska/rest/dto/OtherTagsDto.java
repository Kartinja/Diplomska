package com.example.diplomska.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OtherTagsDto {
    private List<String> foodon;
    private List<String> hansard;
    private String hansardClosest;
    private String hansardParent;
    private List<String> snomedct;
    private List<String> synonyms;
}
