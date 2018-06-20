package com.jms;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class MessageController implements Serializable {

    private static final long serialVersionUID = -8668995686559940396L;
    private static final String USER = "user";
    private static final String TOPIC = "topic";

    @Inject
    private JMSService jmsService;

    private String message;
    private String recipient;
    private String messageType;

    public void send() {
        switch (messageType) {
            case USER:
                jmsService.sendMessageToUser(recipient, message);
                break;
            case TOPIC:
                jmsService.sendMessageToTopic(recipient, message);
                break;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
