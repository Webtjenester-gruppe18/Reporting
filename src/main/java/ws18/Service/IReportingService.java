package ws18.Service;

import ws18.Model.*;

import java.util.ArrayList;

public interface IReportingService {

    DTUPayTransaction getTransactionById(String transactionId);
    ArrayList<CustomerReportTransaction> getCustomerTransactionsByIds(String customerCpr);
    ArrayList<CustomerReportTransaction> getCustomerTransactionsByIdsFromThenToNow(String customerCpr, long fromTime);
    ArrayList<MerchantReportTransaction> getMerchantTransactionsByIds(String merchantCpr);
    ArrayList<MerchantReportTransaction> getMerchantTransactionsByIdsFromThenToNow(String merchantCpr, long fromTime);
    ArrayList<DTUPayTransaction> getAllTransactions();
    String saveTransaction(DTUPayTransaction transaction);

}
