package com.example.diplomska.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Ingredient {
    //@GeneratedValue
    //private long id;
    @Id
    private String name;
    private String url;
    @ManyToMany
    private List<Recipe> recipe;
}
