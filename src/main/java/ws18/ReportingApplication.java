package ws18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ws18.Service.ReportingService;
import ws18.messagingutils.EventReceiverImpl;
import ws18.messagingutils.EventSenderImpl;
import ws18.messagingutils.IEventSender;
import ws18.model.Event;
import ws18.service.LocalReportingService;

@SpringBootApplication
@EnableSwagger2
public class ReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportingApplication.class, args);
		new ReportingApplication().startUp();
	}

	private void startUp() throws Exception {
		IEventSender eventSender = new EventSenderImpl();
		ReportingService reportingService = new ReportingService(eventSender);
		new EventReceiverImpl(reportingService).listen();
	}

}