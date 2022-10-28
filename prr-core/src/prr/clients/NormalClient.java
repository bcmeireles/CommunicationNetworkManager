package prr.clients;

public class NormalClient extends ClientLevel {
    public NormalClient(Client client){
        super(client);
    }

    @Override
    public void normal() {
        // Client is already normal
    }

    @Override
    public void gold() {
        if (_client.getBalance() > 500) {
            _client.setLevel(new GoldClient(_client));
        }
    }

    @Override
    public void platinum() {
        // Not possible
    }

    @Override
    public String toString() {
        return "NORMAL";
    }
}