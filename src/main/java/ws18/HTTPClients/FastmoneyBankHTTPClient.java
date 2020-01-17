package ws18.HTTPClients;

import ws18.Model.Customer;
import ws18.Model.DTUPayTransaction;
import ws18.Model.DTUPayUser;
import ws18.Model.Fastmoney.FastmoneyAccount;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class FastmoneyBankHTTPClient {

    Client c = ClientBuilder.newClient();
    WebTarget w = c.target("http://fastmoney-00.compute.dtu.dk/rest/accounts");

    public String createAccount(FastmoneyAccount fastmoneyAccount) {
        Response dtuPayTransactionResponse =
                w.request().post(Entity.json(fastmoneyAccount));

        return dtuPayTransactionResponse.readEntity(String.class);
    }

    public String deleteAccount(String fastmoneyAccountId) {
        Response fastmoneyDeletion =
                w.path("/" + fastmoneyAccountId).request().delete();

        return fastmoneyDeletion.readEntity(String.class);
    }

}
