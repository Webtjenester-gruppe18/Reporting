package ws18.control;
import ws18.database.*;
import ws18.exception.ExceptionContainer;
import ws18.service.*;

public class ControlReg {
    private static ExceptionContainer exceptionContainer;
    private static IReportingService reportingService;
    private static IReportDatabase reportDatabase;

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
}
