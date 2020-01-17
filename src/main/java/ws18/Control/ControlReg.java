package ws18.Control;
import ws18.Database.*;
import ws18.Exception.ExceptionContainer;
import ws18.HTTPClients.FastmoneyBankHTTPClient;
import ws18.HTTPClients.PaymentHTTPClient;
import ws18.HTTPClients.UserManagerHTTPClient;
import ws18.Service.*;

public class ControlReg {
    private static ExceptionContainer exceptionContainer;
    private static IReportingService reportingService;
    private static IReportDatabase reportDatabase;
    private static UserManagerHTTPClient userManagerHTTPClient;
    private static PaymentHTTPClient paymentHTTPClient;
    private static FastmoneyBankHTTPClient fastMoneyBankHTTPClient;

    public static ExceptionContainer getExceptionContainer() {
        if (exceptionContainer == null) exceptionContainer = new ExceptionContainer();
        return exceptionContainer;
    }

    public static IReportingService getReportingService() {
        if (reportingService == null) reportingService = new ReportingService();
        return reportingService;
    }

    public static IReportDatabase getReportDatabase() {
        if (reportDatabase == null) reportDatabase = new InMemoryReportDatabase();
        return reportDatabase;
    }

    public static UserManagerHTTPClient getUserManagerHTTPClient() {
        if (userManagerHTTPClient == null) userManagerHTTPClient = new UserManagerHTTPClient();
        return userManagerHTTPClient;
    }

    public static PaymentHTTPClient getPaymentHTTPClient() {
        if (paymentHTTPClient == null) paymentHTTPClient = new PaymentHTTPClient();
        return paymentHTTPClient;
    }

    public static FastmoneyBankHTTPClient getFastMoneyBankHTTPClient() {
        if (fastMoneyBankHTTPClient == null) fastMoneyBankHTTPClient = new FastmoneyBankHTTPClient();
        return fastMoneyBankHTTPClient;
    }
}
