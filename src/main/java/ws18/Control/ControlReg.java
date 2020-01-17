package ws18.Control;
import ws18.Database.*;
import ws18.Exception.ExceptionContainer;
import ws18.HTTPClients.UserManagerHTTPClient;
import ws18.Service.*;

public class ControlReg {
    private static ExceptionContainer exceptionContainer;
    private static IReportingService reportingService;
    private static IReportDatabase reportDatabase;
    private static UserManagerHTTPClient userManagerHTTPClient;

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
}
