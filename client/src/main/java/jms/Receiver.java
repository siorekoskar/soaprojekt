package jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

public class Receiver implements MessageListener {

    private ReceiverController receiverController;

    private Logger logger = Logger.getLogger(Receiver.class.getName());

    private final String username;

    public Receiver(ReceiverController receiverController, String username) {
        this.username = username;
        this.receiverController = receiverController;
    }

    @Override
    public void onMessage(Message message) {
        try {
            String messageContent = ((TextMessage) message).getText();
            receiverController.sendMessage(messageContent);
            logger.warning("jestem u uzytkownika: " + username);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
