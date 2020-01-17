package ws18.Service;

import ws18.Control.ControlReg;
import ws18.Database.IReportDatabase;
import ws18.HTTPClients.UserManagerHTTPClient;
import ws18.Model.*;

import java.util.ArrayList;
import java.util.Date;

public class ReportingService implements IReportingService {

    private IReportDatabase reportDatabase = ControlReg.getReportDatabase();
    private UserManagerHTTPClient userManagerHTTPClient = ControlReg.getUserManagerHTTPClient();

    @Override
    public DTUPayTransaction getTransactionById(String transactionId) {
        return this.reportDatabase.getTransactionById(transactionId);
    }

    @Override
    public ArrayList<CustomerReportTransaction> getCustomerTransactionsByIds(String customerCpr) {

        ArrayList<CustomerReportTransaction> result = new ArrayList<>();
        Customer customer = this.userManagerHTTPClient.getCustomerByCpr(customerCpr);

        for (String transactionId : customer.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction != null) {
                CustomerReportTransaction reportTransaction =
                        new CustomerReportTransaction(transaction, customer);

                result.add(reportTransaction);
            }

            /*
            try {
                CustomerReportTransaction reportTransaction =
                        new CustomerReportTransaction(transaction, customer);

                result.add(reportTransaction);
            } catch (NullPointerException ex) {
                ex.getMessage();
            }*/

        }

        return result;
    }

    @Override
    public ArrayList<CustomerReportTransaction> getCustomerTransactionsByIdsFromThenToNow(String customerCpr, long fromTime) {

        ArrayList<CustomerReportTransaction> result = new ArrayList<>();
        Customer customer = this.userManagerHTTPClient.getCustomerByCpr(customerCpr);

        for (String transactionId : customer.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction != null) {
                if (transaction.getTime() > new Date().getTime() - fromTime) {
                    CustomerReportTransaction reportTransaction =
                            new CustomerReportTransaction(transaction, customer);

                    result.add(reportTransaction);
                }
            }
        }

        return result;
    }

    @Override
    public ArrayList<MerchantReportTransaction> getMerchantTransactionsByIds(String merchantCpr) {

        ArrayList<MerchantReportTransaction> result = new ArrayList<>();
        Merchant merchant = this.userManagerHTTPClient.getMerchantByCpr(merchantCpr);

        for (String transactionId : merchant.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction != null) {
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
    public ArrayList<MerchantReportTransaction> getMerchantTransactionsByIdsFromThenToNow(String merchantCpr, long fromTime) {
        ArrayList<MerchantReportTransaction> result = new ArrayList<>();
        Merchant merchant = this.userManagerHTTPClient.getMerchantByCpr(merchantCpr);

        for (String transactionId : merchant.getTransactionIds()) {
            DTUPayTransaction transaction = this.reportDatabase.getTransactionById(transactionId);

            if (transaction != null) {
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
