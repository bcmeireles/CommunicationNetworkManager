package prr.terminals;

import prr.clients.Client;

import java.util.Map;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String id, Client owner){
        super(id, owner);
    }

    public BasicTerminal(String id, Client owner, String state) {
        super(id, owner, state);
    }

    public boolean isFancy(){
        return false;
    }

    @Override
    public String toString() {

        Map<String, Terminal> friends = getFriends();

        if (friends.size() > 0) {
            String toReturn = "BASIC|" + getID() + "|" + getOwner().getID() + "|" + "state" + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt()) + "|";

            for (int i = 0; i < friends.size(); i++) {
                toReturn += friends.get(i).getID();
                if (i + 1 != friends.size()) {
                    toReturn += ",";
                }
            }
            return toReturn;
        }
        else {
            return "BASIC|" + getID() + "|" + getOwner().getID() + "|" + getState().toString() + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt());
        }
    }
}