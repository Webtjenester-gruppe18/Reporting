package ws18.Reporting;

import ws18.Control.ControlReg;
import ws18.HTTPClients.FastmoneyBankHTTPClient;
import ws18.HTTPClients.PaymentHTTPClient;
import ws18.Helper.DateTimeHelper;
import ws18.Model.*;
import ws18.Model.Fastmoney.FastmoneyAccount;
import ws18.Model.Fastmoney.User;
import ws18.Service.IReportingService;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReportingSteps {

    private IReportingService reportingService;
    private PaymentHTTPClient paymentHTTPClient;
    private FastmoneyBankHTTPClient fastmoneyBankHTTPClient;
    private Customer currentCustomer;
    private Merchant currentMerchant;
    private FastmoneyAccount currentFastmoneyCustomer;
    private FastmoneyAccount currentFastMoneyMerchant;
    private ArrayList<CustomerReportTransaction> customerTransactions;
    private ArrayList<MerchantReportTransaction> merchantReportTransactions;

    @Before
    public void setUp() {
        this.reportingService = ControlReg.getReportingService();
        this.paymentHTTPClient = ControlReg.getPaymentHTTPClient();
        this.fastmoneyBankHTTPClient = ControlReg.getFastMoneyBankHTTPClient();
    }

    @Before
    public void setUpAccountCustomer() {
        this.currentFastmoneyCustomer = new FastmoneyAccount();
        this.currentFastmoneyCustomer.setBalance(1000);
        this.currentFastmoneyCustomer.setUser(
                new User(
                        "Jane",
                        "Doe",
                        "888888-2222"
                )
        );

        String currentFastmoneyCustomerAccountId = this.fastmoneyBankHTTPClient.createAccount(this.currentFastmoneyCustomer);

        this.currentCustomer = new Customer();
        this.currentCustomer.setCprNumber(this.currentFastmoneyCustomer.getUser().getCprNumber());
        this.currentCustomer.setFirstName(this.currentFastmoneyCustomer.getUser().getFirstName());
        this.currentCustomer.setLastName(this.currentFastmoneyCustomer.getUser().getLastName());
        this.currentCustomer.setAccountId(currentFastmoneyCustomerAccountId);
        this.currentCustomer.setTransactionIds(new ArrayList<>());
    }

    @Before
    public void setUpAccountMerchant() {
        this.currentFastMoneyMerchant = new FastmoneyAccount();
        this.currentFastMoneyMerchant.setBalance(1000);
        this.currentFastMoneyMerchant.setUser(
                new User(
                        "John",
                        "Doe",
                        "444444-1111"
                )
        );

        String currentFastmoneyMerchantAccountId = this.fastmoneyBankHTTPClient.createAccount(this.currentFastMoneyMerchant);

        this.currentMerchant = new Merchant();
        this.currentMerchant.setCprNumber(this.currentFastMoneyMerchant.getUser().getCprNumber());
        this.currentMerchant.setFirstName(this.currentFastMoneyMerchant.getUser().getFirstName());
        this.currentMerchant.setLastName(this.currentFastMoneyMerchant.getUser().getLastName());
        this.currentMerchant.setAccountId(currentFastmoneyMerchantAccountId);
        this.currentMerchant.setTransactionIds(new ArrayList<>());
    }

    @Given("a registered customer with an account")
    public void aRegisteredCustomerWithAnAccount() {
        /*
        this.currentFastmoneyCustomer = new FastmoneyAccount();
        this.currentFastmoneyCustomer.setBalance(1000);
        this.currentFastmoneyCustomer.setUser(
                new User(
                        "Jane",
                        "Doe",
                        "888888-2222"
                )
        );

        String currentFastmoneyCustomerAccountId = this.fastmoneyBankHTTPClient.createAccount(this.currentFastmoneyCustomer);

        this.currentCustomer = new Customer();
        this.currentCustomer.setCprNumber(this.currentFastmoneyCustomer.getUser().getCprNumber());
        this.currentCustomer.setFirstName(this.currentFastmoneyCustomer.getUser().getFirstName());
        this.currentCustomer.setLastName(this.currentFastmoneyCustomer.getUser().getLastName());
        this.currentCustomer.setAccountId(currentFastmoneyCustomerAccountId);
        this.currentCustomer.setTransactionIds(new ArrayList<>());
        */
    }

    @Given("a registered merchant with an account")
    public void aRegisteredMerchantWithAnAccount() {
        /*
        this.currentFastMoneyMerchant = new FastmoneyAccount();
        this.currentFastMoneyMerchant.setBalance(1000);
        this.currentFastMoneyMerchant.setUser(
                new User(
                        "John",
                        "Doe",
                        "444444-1111"
                )
        );

        String currentFastmoneyMerchantAccountId = this.fastmoneyBankHTTPClient.createAccount(this.currentFastMoneyMerchant);

        this.currentMerchant = new Merchant();
        this.currentMerchant.setCprNumber(this.currentFastMoneyMerchant.getUser().getCprNumber());
        this.currentMerchant.setFirstName(this.currentFastMoneyMerchant.getUser().getFirstName());
        this.currentMerchant.setLastName(this.currentFastMoneyMerchant.getUser().getLastName());
        this.currentMerchant.setAccountId(currentFastmoneyMerchantAccountId);
        this.currentMerchant.setTransactionIds(new ArrayList<>());*/
    }

    @Given("the customer has performed atleast one transaction")
    public void theCustomerHasPerformedAtleastOneTransaction() {

        DTUPayTransaction transaction =
                new DTUPayTransaction(
                        BigDecimal.valueOf(1111),
                        this.currentCustomer.getAccountId(),
                        this.currentMerchant.getAccountId(),
                        "Comment",
                        new Date().getTime(),
                        new Token());

        // String transactionId = this.reportingService.saveTransaction(transaction);
        String transactionId = this.paymentHTTPClient.saveDTUPayTransaction(transaction);
        System.out.println("----- TransactionId " + transactionId);

        this.currentCustomer.getTransactionIds().add(transactionId);

        assertEquals(1, this.currentCustomer.getTransactionIds().size());
    }

    @When("the customer requests for an overview")
    public void theCustomerRequestsForAnOverview() {
        this.customerTransactions = this.reportingService.getCustomerTransactionsByIds(this.currentCustomer.getAccountId());
    }

    @Then("an overview is create with one transaction")
    public void anOverviewIsCreateWithOneTransaction() {
        assertEquals(1, this.customerTransactions.size());
    }

    @Given("the customer has performed atleast one transaction in the last month")
    public void theCustomerHasPerformedAtleastOneTransactionInTheLastMonth() {

        DTUPayTransaction transaction =
                new DTUPayTransaction(
                        BigDecimal.valueOf(1111),
                        this.currentCustomer.getAccountId(),
                        this.currentMerchant.getAccountId(),
                        "Comment",
                        new Date().getTime(),
                        new Token());

        // String transactionId = this.reportingService.saveTransaction(transaction);
        String transactionId = this.paymentHTTPClient.saveDTUPayTransaction(transaction);
        this.currentCustomer.getTransactionIds().add(transactionId);

        assertEquals(1, this.reportingService.getCustomerTransactionsByIds(this.currentCustomer.getCprNumber()).size()); //this.currentCustomer.getTransactionIds().size());
    }

    @When("the customer requests for an monthly overview")
    public void theCustomerRequestsForAnMonthlyOverview() {
        this.customerTransactions = this.reportingService.getCustomerTransactionsByIdsFromThenToNow(this.currentCustomer.getAccountId(), DateTimeHelper.MONTH_IN_MILLIS);
    }

    @Given("the merchant has performed atleast one transaction")
    public void theMerchantHasPerformedAtleastOneTransaction() {
        DTUPayTransaction transaction =
                new DTUPayTransaction(
                        BigDecimal.valueOf(2222),
                        this.currentMerchant.getAccountId(),
                        this.currentCustomer.getAccountId(),
                        "Comment",
                        new Date().getTime(),
                        new Token());

        // String transactionId = this.reportingService.saveTransaction(transaction);
        String transactionId = this.paymentHTTPClient.saveDTUPayTransaction(transaction);
        this.currentMerchant.getTransactionIds().add(transactionId);

        assertEquals(1, this.currentMerchant.getTransactionIds().size());
    }

    @When("the merchant requests for an transaction overview")
    public void theMerchantRequestsForAnTransactionOverview() {
        this.merchantReportTransactions = this.reportingService.getMerchantTransactionsByIds(this.currentMerchant.getAccountId());
    }

    @Then("an merchant transaction overview is created")
    public void anMerchantTransactionOverviewIsCreated() {
        assertEquals(1, this.merchantReportTransactions.size());
    }


    @Given("the merchant has performed one transaction in the last month")
    public void theMerchantHasPerformedOneTransactionInTheLastMonth() {
        DTUPayTransaction transaction =
                new DTUPayTransaction(
                        BigDecimal.valueOf(2222),
                        this.currentMerchant.getAccountId(),
                        this.currentCustomer.getAccountId(),
                        "Comment",
                        new Date().getTime(),
                        new Token());

        String transactionId = this.reportingService.saveTransaction(transaction);

        this.currentMerchant.getTransactionIds().add(transactionId);

        assertEquals(1, this.currentMerchant.getTransactionIds().size());
    }

    @When("the merchant requests for an monthly overview")
    public void theMerchantRequestsForAnMonthlyOverview() {
        this.merchantReportTransactions =
                this.reportingService.getMerchantTransactionsByIdsFromThenToNow(this.currentMerchant.getAccountId(), DateTimeHelper.MONTH_IN_MILLIS);
    }

    @Then("an monthly merchant transaction report is created")
    public void anMonthlyMerchantTransactionReportIsCreated() {
        assertEquals(1, this.merchantReportTransactions.size());
    }

    @After
    public void tearDown() {
        this.fastmoneyBankHTTPClient.deleteAccount(this.currentCustomer.getAccountId());
        this.fastmoneyBankHTTPClient.deleteAccount(this.currentMerchant.getAccountId());
    }
}
