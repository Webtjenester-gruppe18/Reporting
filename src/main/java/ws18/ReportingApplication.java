package ws18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ws18.messagingutils.*;
import ws18.service.ReportingService;

/**
 * @author Ali Moussa, s175119
 */

@SpringBootApplication
@EnableSwagger2
public class ReportingApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReportingApplication.class, args);
        new ReportingApplication().startUp();
    }

    private void startUp() throws Exception {
        IEventSender eventSender = new RabbitMqSender();
        ReportingService reportingService = new ReportingService(eventSender);
        new RabbitMqListener(reportingService).listen();
    }

}