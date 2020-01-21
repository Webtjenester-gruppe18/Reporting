package ws18.messagingutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws18.model.DTUPayTransaction;
import ws18.model.Event;
import ws18.model.EventType;
import java.util.ArrayList;

@Service
public class Listener {
    private ObjectMapper objectMapper;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public Listener(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {RabbitMQValues.REPORTING_SERVICE_QUEUE_NAME})
    public void receiveEvent(Event event) {

        if (event.getType().equals(EventType.REQUEST_TRANSACTIONS_RESPONSE)) {
            ArrayList<DTUPayTransaction> transactions = this.objectMapper.convertValue(event.getObject(), ArrayList.class);

            Event successResponse = new Event(EventType.REQUEST_TRANSACTIONS_SUCCEED, transactions);
            this.rabbitTemplate.convertAndSend(RabbitMQValues.TOPIC_EXCHANGE_NAME, RabbitMQValues.DTU_SERVICE_ROUTING_KEY, successResponse);
        }
    }

}
