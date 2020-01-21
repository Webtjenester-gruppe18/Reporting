package ws18.service;

import ws18.control.ControlReg;
import ws18.database.IReportDatabase;
import ws18.model.CustomerReportTransaction;
import ws18.model.DTUPayTransaction;
import ws18.model.MerchantReportTransaction;

import java.util.ArrayList;

public class ReportingService implements IReportingService {

    private IReportDatabase reportDatabase = ControlReg.getReportDatabase();

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
}
