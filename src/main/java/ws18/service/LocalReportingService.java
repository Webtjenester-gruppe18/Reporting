/*
package ws18.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ws18.control.ControlReg;
import ws18.database.IReportDatabase;
import ws18.messagingutils.IEventReceiver;
import ws18.messagingutils.IEventSender;
import ws18.model.*;

import java.util.ArrayList;
import java.util.Date;

public abstract class LocalReportingService implements ReportingService, IEventReceiver {

    private final ObjectMapper objectMapper;
    private IReportDatabase reportDatabase = ControlReg.getReportDatabase();
    private IEventSender eventSender;

    public LocalReportingService(IEventSender eventSender) {
        this.objectMapper = new ObjectMapper();
        this.eventSender = eventSender;
    }

    @Override
    public DTUPayTransaction getTransactionById(String transactionId) {
        return this.reportDatabase.getTransactionById(transactionId);
    }

    @Override
    public ArrayList<CustomerReportTransaction> getCustomerTransactionsByIds(Customer customer) {

        ArrayList<CustomerReportTransaction> result = new ArrayList<>();

        for (String transactionId : customer.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            CustomerReportTransaction reportTransaction =
                    new CustomerReportTransaction(transaction, customer);

            result.add(reportTransaction);
        }

        return result;
    }

    @Override
    public ArrayList<CustomerReportTransaction> getCustomerTransactionsByIdsFromThenToNow(Customer customer, long fromTime) {

        ArrayList<CustomerReportTransaction> result = new ArrayList<>();

        for (String transactionId : customer.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction.getTime() > new Date().getTime() - fromTime) {
                CustomerReportTransaction reportTransaction =
                        new CustomerReportTransaction(transaction, customer);

                result.add(reportTransaction);
            }
        }

        return result;
    }

    @Override
    public ArrayList<MerchantReportTransaction> getMerchantTransactionsByIds(Merchant merchant) {

        ArrayList<MerchantReportTransaction> result = new ArrayList<>();

        for (String transactionId : merchant.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            MerchantReportTransaction merchantReportTransaction =
                    new MerchantReportTransaction(
                            transaction.getAmount(),
                            transaction.getDescription(),
                            transaction.getTime(),
                            transaction.getToken());

            result.add(merchantReportTransaction);
        }

        return result;
    }

    @Override
    public ArrayList<MerchantReportTransaction> getMerchantTransactionsByIdsFromThenToNow(Merchant merchant, long fromTime) {
        ArrayList<MerchantReportTransaction> result = new ArrayList<>();

        for (String transactionId : merchant.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction.getTime() > new Date().getTime() - fromTime) {
                MerchantReportTransaction merchantReportTransaction =
                        new MerchantReportTransaction(
                                transaction.getAmount(),
                                transaction.getDescription(),
                                transaction.getTime(),
                                transaction.getToken());

                result.add(merchantReportTransaction);
            }
        }

        return result;
    }

    @Override
    public ArrayList<DTUPayTransaction> getAllTransactions() {
        return this.reportDatabase.getAllTransactions();
    }

    @Override
    public String saveTransaction(DTUPayTransaction transaction) {
        return this.reportDatabase.saveTransaction(transaction);
    }

    /*
    @Override
    public void receiveEvent(Event event) throws Exception {
        if (event.getType().equals(EventType.RETRIEVE_CUSTOMER_REPORTS)) {
            CustomerReportTransaction customerReportTransaction = objectMapper.convertValue(event.getObject(), CustomerReportTransaction.class);

            try {
                validateToken(paymentRequest.getCpr(), paymentRequest.getToken());
            } catch (TokenValidationException e) {
                Event response = new Event(EventType.TOKEN_VALIDATION_FAILED, e, RabbitMQValues.DTU_SERVICE_ROUTING_KEY);
                eventSender.sendEvent(response);
                return;
            }
            event.setType(EventType.MONEY_TRANSFER_REQUEST);
            event.setRoutingKey(RabbitMQValues.PAYMENT_SERVICE_ROUTING_KEY);
            eventSender.sendEvent(event);
        } else if (event.getType().equals(EventType.REQUEST_FOR_NEW_TOKENS)) {
            String cpr = objectMapper.convertValue(event.getObject(), String.class);

            try {
                requestForNewTokens(cpr);
            } catch (TooManyTokensException e) {
                Event response = new Event(EventType.TOKEN_GENERATION_FAILED, e, RabbitMQValues.DTU_SERVICE_ROUTING_KEY);
                eventSender.sendEvent(response);
                return;
            }

            Event successResponse = new Event(EventType.TOKEN_GENERATION_SUCCEED, EventType.TOKEN_GENERATION_SUCCEED, RabbitMQValues.DTU_SERVICE_ROUTING_KEY);
            eventSender.sendEvent(successResponse);

        } else if (event.getType().equals(EventType.RETRIEVE_TOKENS)) {
            String cpr = objectMapper.convertValue(event.getObject(), String.class);
            ArrayList<Token> tokens = getUnusedTokensByCpr(cpr);
            Event successResponse = new Event(EventType.RETRIEVE_TOKENS_SUCCEED, tokens, RabbitMQValues.DTU_SERVICE_ROUTING_KEY);
            eventSender.sendEvent(successResponse);
        }
    }

}
*/