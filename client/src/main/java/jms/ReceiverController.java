package jms;

import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.jms.*;
import java.io.Serializable;
import java.security.Principal;

@Named
@SessionScoped
public class ReceiverController implements Serializable {

    private static final long serialVersionUID = -1374573427851527560L;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "java:jboss/jms/queue/messageTopic")
    private Topic topic;

    private String message;

    @PostConstruct
    public void createReceiver() {
        try {
            Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            MessageConsumer messageConsumer = session.createConsumer(topic, "username='" + userName + "'");
            messageConsumer.setMessageListener(new Receiver(this, userPrincipal.getName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void foo() {
//        this.message = null;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void sendMessage(String messageContent) {
        this.message = messageContent;
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/jms", new FacesMessage(messageContent));
    }
}
