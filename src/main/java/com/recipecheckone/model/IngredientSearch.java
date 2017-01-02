package com.recipecheckone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by freya.juniper-nine on 28/11/2016.
 */
@Entity
public class IngredientSearch implements Serializable {


    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String ingredient;

    IngredientSearch() {
    }

    public IngredientSearch(String ingredient) {
        this.ingredient = ingredient;

    }

    public Long getId() {
        return id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "IngredientSearch{" +
                "ingredient='" + ingredient + '\'' +
                '}';
    }
}
