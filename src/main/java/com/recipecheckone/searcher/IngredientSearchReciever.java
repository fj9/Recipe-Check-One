package com.recipecheckone.searcher;

import com.recipecheckone.model.IngredientSearch;
import com.recipecheckone.model.Recipe;
import com.recipecheckone.model.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ajix on 22/12/2016.
 */
@Component
public class IngredientSearchReciever {
    private static final Logger logger = LoggerFactory.getLogger(IngredientSearchReciever.class);


    @Autowired
    private ReplyPublisher replySender;

    @JmsListener(destination = "IngredientSearch")
    public void receiveMessage(IngredientSearch search) {
        logger.info("Received <" + search.getIngredient() + "> with id " + search.getId());
        //curl "https://api.edamam.com/search?q=chicken&
        // app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}"
        RestTemplate restTemplate = new RestTemplate();
        EdamamReply edamamReply = restTemplate.getForObject("https://api.edamam.com/search?q=" + search.getIngredient() +
                "&app_id=62eba3fc&app_key=7c7cd44d123cad8e908fad0f23dd123c", EdamamReply.class);
        final Set<Recipe> recipes = new HashSet<>();
        edamamReply.getHits().forEach(hit -> recipes.add(hit.getRecipe()));
        Reply reply = new Reply(search.getId(), recipes);
        logger.info("Sending reply....");
        replySender.sendMessage(reply);
    }
}
