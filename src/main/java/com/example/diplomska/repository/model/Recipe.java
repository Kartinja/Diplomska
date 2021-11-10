package com.example.diplomska.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String text;
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Ingredient> ingredients;
}
