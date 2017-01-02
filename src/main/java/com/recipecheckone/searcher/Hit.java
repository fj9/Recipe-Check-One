package com.recipecheckone.searcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recipecheckone.model.Recipe;

/**
 * Created by ajix on 02/01/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hit {
    Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
