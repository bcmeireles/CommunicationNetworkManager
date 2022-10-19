package prr.clients;

public abstract class ClientLevel {
    protected Client _client;

    public ClientLevel(Client client) { _client = client; }

    public abstract void normal();
    public abstract void gold();
    public abstract void platinum();
}