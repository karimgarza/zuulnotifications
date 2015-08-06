package demo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Component
public class JmsServiceImpl implements JmsService {

    private Logger log = Logger.getLogger(this.getClass());

    private JmsTemplate jmsTemplate;

    @Value("${queue.name}")
    private String queueName;

    @Autowired
    public JmsServiceImpl(JmsTemplate jmsTemplate){
        this.jmsTemplate=jmsTemplate;
    }

    @Override
    public void sendMessage(NotificationDTO dto){
        jmsTemplate.send(queueName, createMessage(dto));
        log.info("Message sent");
    }

    private MessageCreator createMessage(final NotificationDTO dto){
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(dto);
            }
        };
        return messageCreator;
    }

}
