package com.jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;

@ApplicationScoped
public class JMSService {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:jboss/jms/queue/messageTopic")
    private Topic topic;

    public void sendMessageToUser(String user, String message) {
//        sendMessage(USER, user, message);
    }

    public void sendMessageToTopic(String user, String message) {
        sendMessage("ALL", user, message);
    }

    private void sendMessage(String name, String value, String message) {
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(topic);
            connection.start();
            TextMessage textMessage = session.createTextMessage(message);
//            textMessage.setStringProperty("name", "all");
            messageProducer.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}