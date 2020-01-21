package ws18.model;

import java.math.BigDecimal;

/**
 * @author Ali Moussa, s175119
 */

public class MerchantReportTransaction extends ReportTransaction {

    public MerchantReportTransaction(BigDecimal amount, String description, long time, Token token) {
        super(amount, description, time, token);
    }
}
