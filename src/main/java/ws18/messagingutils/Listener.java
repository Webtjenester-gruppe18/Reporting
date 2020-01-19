package ws18.messagingutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ws18.Model.Event;


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


    }

}
