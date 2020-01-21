package ws18.Reporting;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import ws18.messagingutils.IEventSender;
import ws18.model.*;
import ws18.service.ReportingService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.*;

public class ReportingSteps {

    private ArrayList<DTUPayTransaction> dtuPayTransactions = new ArrayList<>();
    private IEventSender eventSender = mock(IEventSender.class);
    private ReportingService reportingService = new ReportingService(eventSender);

    @When("the service receives the {string} event")
    public void theServiceReceivesTheEvent(String string) throws Exception {
        Event event = new Event();
        event.setType(EventType.valueOf(string));
        if (event.getType().equals(EventType.REQUEST_TRANSACTIONS_RESPONSE)) {

            DTUPayTransaction transaction = new DTUPayTransaction(
                    BigDecimal.valueOf(100),
                    "000000-0000",
                    "111111-1111",
                    "Test",
                    new Date().getTime(),
                    new Token()
            );

            dtuPayTransactions.add(transaction);

            event.setObject(dtuPayTransactions);
        }

        reportingService.receiveEvent(event);
    }

    @Then("the report is created")
    public void theReportIsCreated() {
    }

    @Then("the {string} event is broadcast")
    public void theEventIsBroadcast(String string) throws Exception {
        ArgumentCaptor<Event> argumentCaptor = ArgumentCaptor.forClass(Event.class);
        verify(eventSender, atLeastOnce()).sendEvent(argumentCaptor.capture());
        Assert.assertEquals(EventType.valueOf(string), argumentCaptor.getValue().getType());
    }
}