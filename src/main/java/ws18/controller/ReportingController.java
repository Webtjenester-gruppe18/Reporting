package ws18.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportingController {
    /*
    private IReportingService reportingService = ControlReg.getReportingService();

    @PostMapping(path = "/transactions", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DTUPayTransaction> saveTransaction(@RequestBody DTUPayTransaction transaction) {
        this.reportingService.saveTransaction(transaction);

        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/transactions", method = RequestMethod.GET)
    public ArrayList<DTUPayTransaction> getAllTransactions() {
        return this.reportingService.getAllTransactions();
    }

    @RequestMapping(path = "/transactions/{id}", produces = "application/json", method = RequestMethod.GET)
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
    public ResponseEntity<Object> getCustomerTransactionsByIdsFromThenToNow(@PathVariable @NotNull String id, @PathVariable @NotNull long dateFrom) {
        ArrayList<CustomerReportTransaction> customerTransactionsByIdFromThenToNow = this.reportingService.getCustomerTransactionsByIdsFromThenToNow(id, dateFrom);

        return ResponseEntity.status(HttpStatus.OK).body(customerTransactionsByIdFromThenToNow);
    }

    @RequestMapping(path = "/transactions/merchant/{id}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getMerchantTransactionsByIds(@PathVariable @NotNull String id) {
        ArrayList<MerchantReportTransaction> merchantTransactionsById = this.reportingService.getMerchantTransactionsByIds(id);

        return ResponseEntity.status(HttpStatus.OK).body(merchantTransactionsById);
    }

    @RequestMapping(path = "/transactions/merchant/{id}/{dateFrom}", produces = "application/json", method = RequestMethod.GET)
    public ResponseEntity<Object> getMerchantTransactionsByIdsFromThenToNow(@PathVariable @NotNull String id, @PathVariable @NotNull long dateFrom) {
        ArrayList<MerchantReportTransaction> merchantTransactionsByIdFromThenToNow = this.reportingService.getMerchantTransactionsByIdsFromThenToNow(id, dateFrom);

        return ResponseEntity.status(HttpStatus.OK).body(merchantTransactionsByIdFromThenToNow);
    }

     */
}
