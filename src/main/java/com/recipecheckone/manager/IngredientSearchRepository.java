package com.recipecheckone.manager;

import com.recipecheckone.model.IngredientSearch;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ajix on 29/12/2016.
 */
public interface IngredientSearchRepository extends JpaRepository<IngredientSearch, Long> {
}
