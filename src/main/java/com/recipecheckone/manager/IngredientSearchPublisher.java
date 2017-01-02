package com.recipecheckone.manager;

import com.recipecheckone.model.IngredientSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


/**
 * Created by freya.juniper-nine on 28/11/2016.
 */
@Component
public class IngredientSearchPublisher {
    private static final Logger logger = LoggerFactory.getLogger(IngredientSearchPublisher.class);

    @Autowired
    @Qualifier("jms_template_search")
    private JmsTemplate jmsTemplateSearch;

    public void sendMessage(IngredientSearch ingredient) {
        logger.debug("Sending messages to Queue");
        jmsTemplateSearch.convertAndSend(ingredient);
    }
}
