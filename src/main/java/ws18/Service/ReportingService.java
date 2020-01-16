package ws18.Service;

import ws18.Control.ControlReg;
import ws18.Database.IReportDatabase;
import ws18.Model.*;

import java.util.ArrayList;
import java.util.Date;

public class ReportingService implements IReportingService {

    private IReportDatabase reportDatabase = ControlReg.getReportDatabase();

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
}
