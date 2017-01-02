package com.recipecheckone;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.Arrays;

/**
 * Created by freya.juniper-nine on 28/11/2016.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Value("${broker.url}")
    private String brokerUrl;

    @Value("${search.queue.name}")
    private String searchQueue;

    @Value("${reply.queue.name}")
    private String replyQueue;

    @Value("${max.pool.connection.to.queue}")
    private int maxConnections;

    @Value("${max.sessions.alive}")
    private int maxSessionsAlive;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        activeMQConnectionFactory.setTrustedPackages(Arrays.asList("java.lang","java.util","com.recipecheckone.model"));
        return activeMQConnectionFactory;
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public PooledConnectionFactory pooledConnectionFactory() {

        final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(connectionFactory());
        pooledConnectionFactory.setMaxConnections(maxConnections);
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(maxSessionsAlive);
        return pooledConnectionFactory;
    }

    @Bean
    public ActiveMQQueue activeMQQueueSearch() {
        return new ActiveMQQueue(searchQueue);
    }

    @Bean
    public ActiveMQQueue activeMQQueueReply() {
        return new ActiveMQQueue(replyQueue);
    }

    @Bean(name = "jms_template_search")
    public JmsTemplate jmsTemplateSearch() {
        JmsTemplate jmsTemplate = getJmsTemplate();
        jmsTemplate.setDefaultDestination(activeMQQueueSearch());
        return jmsTemplate;
    }

    @Bean(name = "jms_template_reply")
    public JmsTemplate jmsTemplateReply() {
        JmsTemplate jmsTemplate = getJmsTemplate();
        jmsTemplate.setDefaultDestination(activeMQQueueReply());
        return jmsTemplate;
    }

    private JmsTemplate getJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(pooledConnectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        // This provides all boot's default to this factory, including the message converter;
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }


    //    @Bean
//    public MessageConverter messageConverter(){
////        MarshallingMessageConverter converter = new MarshallingMessageConverter();
//        Jaxb2Marhsaller jaxbMarshaller = new Jaxb2Marhsaller ();
//        jaxbMarshaller.setPackagesToScan("mypackage.jms.model");
//        converter.setUnmarshaller(jaxbMarshaller);
//        converter.setMarshaller(jaxbMarshaller);
//        return converter;
//    }
//    @Bean
//    public MessageConverter messageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
//    @Bean // Serialize message content to json using TextMessage
//    public MessageConverter jacksonJmsMessageConverter() {
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setTargetType(MessageType.TEXT);
//        converter.setTypeIdPropertyName("_type");
//        return converter;
//    }
}
