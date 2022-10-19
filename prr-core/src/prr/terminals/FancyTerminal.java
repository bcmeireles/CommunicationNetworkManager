package prr.terminals;

import prr.clients.Client;

import java.util.Map;
import java.util.TreeMap;

public class FancyTerminal extends BasicTerminal {

    public FancyTerminal(String id, Client owner){
        super(id, owner);
    }

    public FancyTerminal(String id, Client owner, String state) {
        super(id, owner, state);
    }

    public void startVideoCommunication(int receiverID){
        // TODO
    }

    @Override
    public boolean isFancy(){
        return true;
    }

    @Override
    public String toString() {

        Map<String, Terminal> friends = getFriends();

        if (friends.size() > 0) {
            String toReturn = "FANCY|" + getID() + "|" + getOwner().getID() + "|" + "state" + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt()) + "|";

            for (int i = 0; i < friends.size(); i++) {
                toReturn += friends.get(i).getID();
                if (i + 1 != friends.size()) {
                    toReturn += ",";
                }

            }
            return toReturn;
        }
        else {
            return "FANCY|" + getID() + "|" + getOwner().getID() + "|" + getState().toString() + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt());
        }
    }
}