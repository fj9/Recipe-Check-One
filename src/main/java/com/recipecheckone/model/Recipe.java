package com.recipecheckone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by ajix on 02/01/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Recipe implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String uri;
    private String label;
    private String image;

    @JsonIgnore
    @ManyToOne
    private Reply reply;

    Recipe() {
    }

    public Recipe(String uri, String label, String image) {
        this.uri = uri;
        this.label = label;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public Long getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Reply getReply() {
        return reply;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "uri='" + uri + '\'' +
                ", label='" + label + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
