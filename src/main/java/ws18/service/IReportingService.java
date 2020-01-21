package ws18.service;

import ws18.model.*;

import java.util.ArrayList;

/**
 * @author Ali Moussa, s175119
 */

public interface IReportingService {

    DTUPayTransaction getTransactionById(String transactionId);
    ArrayList<CustomerReportTransaction> getCustomerTransactionsByIds(String customerCpr);
    ArrayList<CustomerReportTransaction> getCustomerTransactionsByIdsFromThenToNow(String customerCpr, long fromTime);
    ArrayList<MerchantReportTransaction> getMerchantTransactionsByIds(String merchantCpr);
    ArrayList<MerchantReportTransaction> getMerchantTransactionsByIdsFromThenToNow(String merchantCpr, long fromTime);
    ArrayList<DTUPayTransaction> getAllTransactions();
    String saveTransaction(DTUPayTransaction transaction);

}
