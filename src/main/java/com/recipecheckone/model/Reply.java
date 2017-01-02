package com.recipecheckone.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ajix on 29/12/2016.
 */
@Entity
public class Reply implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private Long searchId;
    @OneToMany(mappedBy = "reply")
    private Set<Recipe> recipes = new HashSet<>();

    Reply (){

    }

    public Reply(Long searchId, Set<Recipe> recipes) {
        this.searchId = searchId;
        this.recipes = recipes;
    }

    public Long getId() {
        return id;
    }

    public Long getSearchId() {
        return searchId;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", searchId=" + searchId +
                ", recipes=" + recipes +
                '}';
    }
}
