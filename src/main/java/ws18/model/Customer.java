package ws18.model;

/**
 * @author Ali Moussa, s175119
 */

public class Customer extends DTUPayUser {

    public Customer() {
    }

    public Customer(String accountId, String firstName, String lastName, String cprNumber) {
        super(accountId, firstName, lastName, cprNumber);
    }
}
