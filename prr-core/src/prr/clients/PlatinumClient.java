package prr.clients;

public class PlatinumClient extends ClientLevel {
    public PlatinumClient(Client client){
        super(client);
    }

    @Override
    public void normal() {
        if (_client.getBalance() < 0) {
            _client.setLevel(new NormalClient(_client));
        }
    }

    @Override
    public void gold() {
        // 2 text communications in a row and non-negative balance
        if (_client.getTextStreak() == 2 && _client.getBalance() > 0) {
            _client.setLevel(new GoldClient(_client));
        }
    }

    @Override
    public void platinum() {
        // Client is already platinum
    }

    public double getTextCost(int length) {
        return _client.getTariff().getTextCostPlatinum(length);
    }

    public double getVoiceCost(int duration) {
        return _client.getTariff().getVoiceCostPlatinum(duration);
    }

    public double getVideoCost(int duration) {
        return _client.getTariff().getVideoCostPlatinum(duration);
    }

    @Override
    public String toString() {
        return "PLATINUM";
    }
}