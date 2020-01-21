package ws18.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ws18.control.ControlReg;
import ws18.database.IReportDatabase;
import ws18.messagingutils.IEventReceiver;
import ws18.messagingutils.IEventSender;
import ws18.messagingutils.RabbitMQValues;
import ws18.model.*;

import java.util.ArrayList;

public class ReportingService implements IReportingService, IEventReceiver {

    private final ObjectMapper objectMapper;
    private IReportDatabase reportDatabase = ControlReg.getReportDatabase();
    private IEventSender eventSender;

    public ReportingService(IEventSender eventSender) {
        this.objectMapper = new ObjectMapper();
        this.eventSender = eventSender;
    }


    @Override
    public DTUPayTransaction getTransactionById(String transactionId) {
        return this.reportDatabase.getTransactionById(transactionId);
    }

    @Override
    public ArrayList<CustomerReportTransaction> getCustomerTransactionsByIds(String customerCpr) {

        ArrayList<CustomerReportTransaction> result = new ArrayList<>();

        /*
        for (String transactionId : customer.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            CustomerReportTransaction reportTransaction =
                    new CustomerReportTransaction(transaction, customer);

            result.add(reportTransaction);
        }*/

        return result;
    }

    @Override
    public ArrayList<CustomerReportTransaction> getCustomerTransactionsByIdsFromThenToNow(String customerCpr, long fromTime) {

        ArrayList<CustomerReportTransaction> result = new ArrayList<>();

        /*
        for (String transactionId : customer.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction.getTime() > new Date().getTime() - fromTime) {
                CustomerReportTransaction reportTransaction =
                        new CustomerReportTransaction(transaction, customer);

                result.add(reportTransaction);
            }
        }*/

        return result;
    }

    @Override
    public ArrayList<MerchantReportTransaction> getMerchantTransactionsByIds(String merchantCpr) {

        ArrayList<MerchantReportTransaction> result = new ArrayList<>();

        /*
        for (String transactionId : merchant.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            MerchantReportTransaction merchantReportTransaction =
                    new MerchantReportTransaction(
                            transaction.getAmount(),
                            transaction.getDescription(),
                            transaction.getTime(),
                            transaction.getToken());

            result.add(merchantReportTransaction);
        }*/

        return result;
    }

    @Override
    public ArrayList<MerchantReportTransaction> getMerchantTransactionsByIdsFromThenToNow(String merchantCpr, long fromTime) {
        ArrayList<MerchantReportTransaction> result = new ArrayList<>();

        /*
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
        }*/

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

    @Override
    public void receiveEvent(Event event) throws Exception {
        if (event.getType().equals(EventType.REQUEST_TRANSACTIONS_RESPONSE)) {
            ArrayList<DTUPayTransaction> transactions = this.objectMapper.convertValue(event.getObject(), ArrayList.class);

            Event successResponse = new Event(EventType.REQUEST_TRANSACTIONS_SUCCEED, transactions, RabbitMQValues.DTU_SERVICE_ROUTING_KEY);
            this.eventSender.sendEvent(successResponse);
        }
    }
}
