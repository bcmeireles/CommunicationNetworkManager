package prr.clients;

import java.io.Serializable;

public abstract class ClientLevel implements Serializable {
    protected Client _client;

    public ClientLevel(Client client) { _client = client; }

    public abstract void normal();
    public abstract void gold();
    public abstract void platinum();

    public abstract double getTextCost(int length);
    public abstract double getVoiceCost(int duration);
    public abstract double getVideoCost(int duration);
}