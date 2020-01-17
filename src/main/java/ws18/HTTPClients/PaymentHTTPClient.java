package ws18.HTTPClients;

import ws18.Model.DTUPayTransaction;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class PaymentHTTPClient {

    Client c = ClientBuilder.newClient();
    WebTarget w = c.target("http://fastmoney-18.compute.dtu.dk:7474/payments");

    public String saveDTUPayTransaction(DTUPayTransaction transaction) {
        Response dtuPayTransactionResponse =
                w.request().post(Entity.json(transaction));

        return dtuPayTransactionResponse.readEntity(String.class);
    }

}
