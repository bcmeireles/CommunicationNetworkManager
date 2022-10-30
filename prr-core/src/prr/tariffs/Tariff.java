package prr.tariffs;

import java.io.Serializable;

import prr.terminals.Client;

public abstract class Tariff implements Serializable {
    /** Client */
    protected Client _client;

    /** @param client is the client */
    public Tariff(Client client) { _client = client; }

    /** Base tariff */
    public abstract void base();
}