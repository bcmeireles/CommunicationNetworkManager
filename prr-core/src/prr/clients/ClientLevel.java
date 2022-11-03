package prr.clients;

import java.io.Serializable;

public abstract class ClientLevel implements Serializable {
    protected Client _client;

    public ClientLevel(Client client) { 
        _client = client; 
        _client.resetTextStreak();
        _client.resetVideoStreak();
    }

    public abstract void normal();
    public abstract void gold();
    public abstract void platinum();

    public abstract long getTextCost(int length);
    public abstract long getVoiceCost(int duration);
    public abstract long getVideoCost(int duration);
}