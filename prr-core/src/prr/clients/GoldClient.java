package prr.clients;

public class GoldClient extends ClientLevel {
    public GoldClient(Client client){
        super(client);
    }

    @Override
    public void normal() {
        // negative balance
        if (_client.getBalance() < 0) {
            _client.setLevel(new NormalClient(_client));
        }
    }

    @Override
    public void gold() {
        // Client is already gold
    }

    @Override
    public void platinum() {
        // 5 video communications in a row and non-negative balance
        if (_client.getBalance() > 0 && _client.getVideoStreak() == 5) {
            _client.setLevel(new PlatinumClient(_client));
        }
    }

    @Override
    public String toString() {
        return "GOLD";
    }
}