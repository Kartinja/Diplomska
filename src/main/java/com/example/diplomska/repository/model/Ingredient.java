package com.example.diplomska.repository.model;

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
public class Ingredient {
    @Id
    private String name;
    private String url;
    @ManyToMany
    private List<Recipe> recipe;
}
