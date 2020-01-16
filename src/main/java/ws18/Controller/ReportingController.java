package ws18.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws18.Control.ControlReg;
import ws18.Model.CustomerReportTransaction;
import ws18.Model.DTUPayTransaction;
import ws18.Model.MerchantReportTransaction;
import ws18.Service.IReportingService;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class ReportingController {

    private IReportingService reportingService = ControlReg.getReportingService();

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public String index() {
        return "Greetings from report endpoint";
    }

    @PostMapping(path = "/transactions", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DTUPayTransaction> saveTransaction(@RequestBody DTUPayTransaction transaction) {
        this.reportingService.saveTransaction(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    public ArrayList<DTUPayTransaction> getAllTransactions() {
        return this.reportingService.getAllTransactions();
    }

    @RequestMapping(path = "/transaction/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<DTUPayTransaction> getTransactionById(@PathVariable @NotNull String id) {
        DTUPayTransaction transactionByIdFromReportingService = this.reportingService.getTransactionById(id);

        return new ResponseEntity<>(transactionByIdFromReportingService, HttpStatus.OK);
    }

    @RequestMapping(path = "/transactions/customer/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerTransactionsByIds(@PathVariable @NotNull String id) {
        ArrayList<CustomerReportTransaction> customerTransactionsById = this.reportingService.getCustomerTransactionsByIds(id);

        return ResponseEntity.status(HttpStatus.OK).body(customerTransactionsById);
    }

    @RequestMapping(path = "/transactions/customer/{id}/{dateFrom}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getCustomerTransactionsByIdsFromThenToNow(@PathVariable @NotNull String id, String dateFrom) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy");
        long dateFromInMS = 0;

        try {
            Date date = simpleDateFormat.parse(dateFrom);
            dateFromInMS = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<CustomerReportTransaction> customerTransactionsByIdFromThenToNow = this.reportingService.getCustomerTransactionsByIdsFromThenToNow(id, dateFromInMS);

        return ResponseEntity.status(HttpStatus.OK).body(customerTransactionsByIdFromThenToNow);
    }

    @RequestMapping(path = "/transactions/merchant/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getMerchantTransactionsByIds(@PathVariable @NotNull String id) {
        ArrayList<MerchantReportTransaction> merchantTransactionsById = this.reportingService.getMerchantTransactionsByIds(id);

        return ResponseEntity.status(HttpStatus.OK).body(merchantTransactionsById);
    }

    @RequestMapping(path = "/transactions/merchant/{id}/{dateFrom}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getMerchantTransactionsByIdsFromThenToNow(@PathVariable @NotNull String id, String dateFrom) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMMyyyy");
        long dateFromInMS = 0;

        try {
            Date date = simpleDateFormat.parse(dateFrom);
            dateFromInMS = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<MerchantReportTransaction> merchantTransactionsByIdFromThenToNow = this.reportingService.getMerchantTransactionsByIdsFromThenToNow(id, dateFromInMS);

        return ResponseEntity.status(HttpStatus.OK).body(merchantTransactionsByIdFromThenToNow);
    }

}
