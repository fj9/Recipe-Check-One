package com.recipecheckone.searcher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by ajix on 02/01/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class EdamamReply {
    String q;
    List<Hit> hits;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }
}
