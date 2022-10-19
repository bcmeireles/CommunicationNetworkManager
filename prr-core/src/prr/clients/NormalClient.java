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
        // Balance above 500 after payment

        _client.setLevel(new GoldClient(_client));

    }

    @Override
    public void platinum() {
        // Not possible

    }
}