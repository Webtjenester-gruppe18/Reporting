package ws18.Model;

import java.math.BigDecimal;

public abstract class ReportTransaction {

    private BigDecimal amount;
    private String description;
    private long time;
    private Token token;

    public ReportTransaction(BigDecimal amount, String description, long time, Token token) {
        this.amount = amount;
        this.description = description;
        this.time = time;
        this.token = token;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public long getTime() {
        return time;
    }

    public Token getToken() {
        return token;
    }
}
