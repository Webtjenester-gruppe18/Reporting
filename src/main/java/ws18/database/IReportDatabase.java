package ws18.database;

import ws18.model.DTUPayTransaction;

import java.util.ArrayList;

public interface IReportDatabase {

    DTUPayTransaction getTransactionById(String transactionId);
    ArrayList<DTUPayTransaction> getAllTransactions();
    String saveTransaction(DTUPayTransaction transaction);
}
