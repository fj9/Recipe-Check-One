package com.recipecheckone.manager;

import com.recipecheckone.model.IngredientSearch;
import com.recipecheckone.model.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ajix on 02/01/2017.
 */
@Controller
public class IngredientSearchWebSocketController {

    @Autowired
    private IngredientSearchRepository ingredientSearchRepository;
    @Autowired
    private IngredientSearchPublisher publisher;
    @Autowired
    private RecipeReplyRepository repository;
    @Autowired
    private SimpMessagingTemplate template;
    private static final Logger logger = LoggerFactory.getLogger(IngredientSearchWebSocketController.class);

    @MessageMapping("/search2")
    public void search(IngredientSearch search) throws Exception {
        IngredientSearch ingredientSearch = ingredientSearchRepository.save(new IngredientSearch(search.getIngredient()));
        publisher.sendMessage(ingredientSearch);
    }


    @JmsListener(destination = "RecipeReply")
    public void receiveMessage(Reply reply) {
        logger.info("Received " + reply.toString());
        Reply savedReply = repository.save(new Reply(reply.getSearchId(), reply.getRecipes()));
        reply(savedReply);
    }

    @RequestMapping(path = "/topic/recipereply", method = RequestMethod.POST)
    private void reply(Reply savedReply) {
        logger.info("Sending 2" + savedReply.toString());
        template.convertAndSend("/topic/recipereply", savedReply);
    }

}
