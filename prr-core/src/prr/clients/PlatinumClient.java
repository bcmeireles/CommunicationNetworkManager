package prr.clients;

public class PlatinumClient extends Client {
    
    public Client(String id, Integrer taxID, String name) {
        super(id, taxID, name);
    }

    public String getRank() {
        return "PLATINUM";
    }
}