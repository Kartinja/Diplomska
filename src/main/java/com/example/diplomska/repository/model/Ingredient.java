package com.example.diplomska.repository.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Ingredient {
    @Id
    private String name;
    private Float protein;
    private Float fat;
    private Float carbohydrate;
    private Float energy;
    private int apiID;


    @ManyToMany
    private List<Recipe> recipe;
}
