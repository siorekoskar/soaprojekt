package com.jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;
import java.util.ArrayList;
import java.util.Set;

@ApplicationScoped
public class JMSService {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:jboss/jms/queue/messageTopic")
    private Topic topic;

    public void sendMessage(String name, Set<String> usernames, String message) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);
            connection.start();
            TextMessage textMessage = session.createTextMessage(message);
            for (String username : new ArrayList<>(usernames)) {
                textMessage.setStringProperty("username", username);
                messageProducer.send(textMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}