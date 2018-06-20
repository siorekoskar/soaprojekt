package jms;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.io.Serializable;
import java.util.StringJoiner;

@Named
@SessionScoped
public class ReceiverController implements Serializable {

    private static final long serialVersionUID = -1374573427851527560L;

    private String username;
    private String topics;
    private String message;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:jboss/jms/queue/messageTopic")
    private Topic topic;

    public void createReceiver(){
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            MessageConsumer messageConsumer = session.createConsumer(topic);//, ""prepareSelector());
            messageConsumer.setMessageListener(new Receiver(this));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void foo(){}

    private String prepareSelector() {
        StringJoiner stringJoiner = new StringJoiner("OR", "(", ")");
        for (String topic : topics.split(",")) {
            stringJoiner.add(String.format("(topic = '%s')", topic));
        }
        stringJoiner.add(String.format("(user = '%s')", username));
        return stringJoiner.toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
