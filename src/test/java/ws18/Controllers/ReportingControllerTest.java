package ws18.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ws18.Controller.ReportingController;
import ws18.Helper.DateTimeHelper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportingControllerTest {

    private ReportingController reportingController = new ReportingController();
    private String customerCpr = "0000-0000";
    private String merchantCpr = "1111-0000";
    private String transactionId = "a7436627-ab02-4ecd-9162-43f610cbc427";
    private long dateFrom = DateTimeHelper.MONTH_IN_MILLIS;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAllTransactions() throws Exception {
        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk());
    }

    @Test
    void getTransactionById() throws Exception {
        mockMvc.perform(get("/transactions/" + transactionId))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerTransactionsByIds() throws Exception {
        mockMvc.perform(get("/transactions/customer/" + customerCpr))
                .andExpect(status().isOk());
    }

    @Test
    void getMerchantTransactionsByIds() throws Exception {
        mockMvc.perform(get("/transactions/merchant/" + merchantCpr))
                .andExpect(status().isOk());
    }

    @Test
    void getCustomerTransactionsByIdsFromThenToNow() throws Exception {
        mockMvc.perform(get("/transactions/customer/" + customerCpr + "/" + dateFrom))
                .andExpect(status().isOk());
    }

    @Test
    void getMerchantTransactionsByIdsFromThenToNow() throws Exception {
        mockMvc.perform(get("/transactions/merchant/" + merchantCpr + "/" + dateFrom))
                .andExpect(status().isOk());
    }

}
