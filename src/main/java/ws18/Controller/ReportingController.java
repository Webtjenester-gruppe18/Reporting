package ws18.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws18.Control.ControlReg;
import ws18.Model.DTUPayTransaction;
import ws18.Service.IReportingService;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

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

    /*
    @RequestMapping(path = "/report/customer/{}")
    public ResponseEntity<Object> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        //return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }*/

}
