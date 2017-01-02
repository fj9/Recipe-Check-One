package com.recipecheckone;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

/**
 * Created by freya.juniper-nine on 12/11/2016.
 */
@SpringBootApplication
@EnableJms
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(Application.class);

    }
}



//    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    @org.springframework.context.annotation.Configuration
//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .httpBasic()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
//                    .anyRequest().authenticated();
//        }
//    }
//    @Bean
//    CommandLineRunner init(ContactRepository contactRepository , ActivityRepository activityRepository){
//        return (evt) -> Arrays.asList("freya", "tony").forEach(
//               c ->{
//                   Contact contact = contactRepository.save(new Contact(c));
//                   activityRepository.save(new Activity( contact, "Dance", "Fun", "Exercise"));
//
//               }
//        );
//
//    }
//}
