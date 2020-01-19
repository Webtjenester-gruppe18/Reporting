package ws18.HTTPClients;

import ws18.Model.Customer;
import ws18.Model.Merchant;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class UserManagerHTTPClient {

    Client c = ClientBuilder.newClient();
    WebTarget w = c.target("http://fastmoney-18.compute.dtu.dk:7676/usermanager/");

    public Customer getCustomerByCpr(String cpr) {
        Customer customer = w.path("customer/")
                .path(cpr)
                .request()
                .get(Customer.class);

        return customer;
    }

    public Merchant getMerchantByCpr(String cpr) {
        Merchant merchant = w.path("merchant/")
                .path(cpr)
                .request()
                .get(Merchant.class);

        return merchant;
    }
}
