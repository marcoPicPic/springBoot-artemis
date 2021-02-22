package com.codenotfound.jms;

import com.codenotfound.domain.User;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.concurrent.CountDownLatch;

public class Receiver {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);


    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "${jms.queue.destination}")
    public void receive(Object message) {
        try {
            if (message instanceof ActiveMQTextMessage) {
                extracted((ActiveMQTextMessage) message);
            } else {
                extracted((ObjectMessage) message);
            }
        } catch (JMSException e) {
            LOGGER.error("{}", e.getMessage());
        }

        latch.countDown();
    }

    private void extracted(ActiveMQTextMessage message) throws JMSException {
        ActiveMQTextMessage amqMessage = message;
        LOGGER.info("essage text :{}", amqMessage.getText());
    }

    private void extracted(ObjectMessage message) throws JMSException {
        ObjectMessage objMsg = message;

        if (objMsg.getObject() instanceof User) {
            User user = (User) message.getObject();
            LOGGER.info("Message Object user : {} {}",
                    user.getName(),
                    user.getFirstName());

        } else {
            LOGGER.error("Type de message inconnu");
        }

    }
}
