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

        StringBuilder sb = new StringBuilder();

        if (friends.size() > 0) {
            sb.append("BASIC|" + getID() + "|" + getOwner().getID() + "|" + "state" + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt()) + "|");

            for (Terminal friend : friends.values()) {
                sb.append(friend.getID() + ",");
            }

            sb.deleteCharAt(sb.length() - 1);
		    return sb.toString();
        }
        else {
            return "BASIC|" + getID() + "|" + getOwner().getID() + "|" + getState().toString() + "|" + Math.round(getPayments()) + "|" + Math.round(getDebt());
        }
    }
}