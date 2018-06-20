package jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Receiver implements MessageListener {

    private ReceiverController receiverController;

    public Receiver(ReceiverController receiverController) {
        this.receiverController = receiverController;
    }

    @Override
    public void onMessage(Message message) {
        try {
            String messageContent = ((TextMessage) message).getText();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
