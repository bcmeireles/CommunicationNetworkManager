package prr.tariffs;

import prr.clients.Client;

public class BaseTariff extends Tariff {
    
    public BaseTariff(Client client) {
        super(client);
    }

    @Override
    public void base() {
        // Tariff is already base
    }


    @Override
    public String toString() {
        return "BASE";
    }

}