package ws18.Service;

import ws18.Model.*;

import java.util.ArrayList;

public interface IReportingService {

    DTUPayTransaction getTransactionById(String transactionId);
    ArrayList<CustomerReportTransaction> getCustomerTransactionsByIds(Customer customer);
    ArrayList<CustomerReportTransaction> getCustomerTransactionsByIdsFromThenToNow(Customer customer, long fromTime);
    ArrayList<MerchantReportTransaction> getMerchantTransactionsByIds(Merchant merchant);
    ArrayList<MerchantReportTransaction> getMerchantTransactionsByIdsFromThenToNow(Merchant merchant, long fromTime);
    ArrayList<DTUPayTransaction> getAllTransactions();
    String saveTransaction(DTUPayTransaction transaction);

}
