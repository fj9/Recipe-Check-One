package com.recipecheckone.searcher;

import com.recipecheckone.model.IngredientSearch;
import com.recipecheckone.model.Reply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ajix on 29/12/2016.
 */

@Component
public class ReplyPublisher {
    private static final Logger logger = LoggerFactory.getLogger(ReplyPublisher.class);

    @Autowired
    @Qualifier("jms_template_reply")
    private JmsTemplate jmsTemplateReply;

    public void sendMessage(Reply reply) {
        logger.debug("Sending messages to Queue");
        logger.info("Sending message " + reply);
        jmsTemplateReply.convertAndSend(reply);
    }
}
