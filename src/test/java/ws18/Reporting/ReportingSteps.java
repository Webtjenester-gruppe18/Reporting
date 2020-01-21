package ws18.Reporting;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ws18.messagingutils.IEventSender;
import ws18.model.Customer;
import ws18.model.CustomerReportTransaction;
import ws18.model.Merchant;
import ws18.model.MerchantReportTransaction;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class ReportingSteps {

    private Customer currentCustomer;
    private Merchant currentMerchant;
    private ArrayList<CustomerReportTransaction> customerTransactions;
    private ArrayList<MerchantReportTransaction> merchantReportTransactions;
    private IEventSender eventSender = mock(IEventSender.class);
    //private ILocalReportingService localReportingService = new LocalReportingService(eventSender);
    private String errormessage = "";

    @When("the service receives the {string} event")
    public void theServiceReceivesTheEvent(String string) {
        /*
        Event event = new Event();
        event.setType(EventType.valueOf(string));
        if (event.getType().equals(EventType.REQUEST_FOR_NEW_TOKENS)||event.getType().equals(EventType.RETRIEVE_TOKENS)) {
            event.setObject("123");
        }else if(event.getType().equals(EventType.TOKEN_VALIDATION_REQUEST)){
            Token token = tokenManager.generateToken("456");
            PaymentRequest request= new PaymentRequest();
            request.setAmount(BigDecimal.ONE);
            request.setCpr("456");
            request.setDescription("Test");
            request.setToken(token);
            event.setObject(request);
        }

        tokenManager.receiveEvent(event);*/
    }

    @Then("the report is created")
    public void theReportIsCreated() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("the {string} event is broadcast")
    public void theEventIsBroadcast(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }
}