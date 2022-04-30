package com.example.diplomska.repository.model;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@DynamicInsert
@EqualsAndHashCode
public class Recipe {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Column(columnDefinition = "varchar")
    private String text;
    private Float fatValue;
    private Float proteinValue;
    private Float carbohydrateValue;
    private Float energyValue;
    @ManyToMany
    @JsonMerge
    private List<Ingredient> ingredients;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] image;
}
