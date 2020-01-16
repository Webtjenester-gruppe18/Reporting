package ws18.Control;
import Bank.InMemoryBankService;
import Database.*;
import Exception.ExceptionContainer;
import Service.*;

public class ControlReg {
    private static ExceptionContainer exceptionContainer;
    private static IBankService bank;
    private static ITokenDatabase tokenDatabase;
    private static ITokenManager tokenManager;
    private static dtu.ws.fastmoney.BankService bankService;
    private static IPaymentService paymentService;
    private static IUserDatabase userDatabase;
    private static IUserService userService;
    private static IReportingService reportingService;
    private static IReportDatabase reportDatabase;

    public static IUserService getUserService() {
        if (userService == null) userService = new UserService();
        return userService;
    }

    public static IUserDatabase getUserDatabase() {
        if (userDatabase == null) userDatabase = new InMemoryUserDatabase();
        return userDatabase;
     }

    public static IBankService getBankService() {
        if (bank == null) bank = new BankService();
        return bank;
    }

    public static ExceptionContainer getExceptionContainer() {
        if (exceptionContainer == null) exceptionContainer = new ExceptionContainer();
        return exceptionContainer;
    }

    public static ITokenDatabase getTokenDatabase() {
        if (tokenDatabase == null) tokenDatabase = new InMemoryTokenDatabase();
        return tokenDatabase;
    }

    public static ITokenManager getTokenManager() {
        if (tokenManager == null) tokenManager = new TokenManager();
        return tokenManager;
    }

    public static dtu.ws.fastmoney.BankService getFastMoneyBankService() {
//        if (bankService == null) bankService = new BankServiceService().getBankServicePort();
        if (bankService == null) bankService = new InMemoryBankService();
        return bankService;
    }

    public static IPaymentService getPaymentService() {
        if (paymentService == null) paymentService = new PaymentService();
        return paymentService;
    }

    public static IReportingService getReportingService() {
        if (reportingService == null) reportingService = new ReportingService();
        return reportingService;
    }

    public static IReportDatabase getReportDatabase() {
        if (reportDatabase == null) reportDatabase = new InMemoryReportDatabase();
        return reportDatabase;
    }
}
