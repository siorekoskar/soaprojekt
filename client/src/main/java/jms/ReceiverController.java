package jms;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
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

    public void createReceiver() {
        try {
            Principal userPrincipal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            connection.start();
            String userName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
//            MessageConsumer messageConsumer = session.createConsumer(topic, "username = " + "'" + userName + "'");
            MessageConsumer messageConsumer = session.createConsumer(topic, String.format("username = '%s'", userName));//, "ALL");
            messageConsumer.setMessageListener(new Receiver(this, userPrincipal.getName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
