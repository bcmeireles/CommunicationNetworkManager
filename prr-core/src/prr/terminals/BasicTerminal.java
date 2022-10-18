package prr.terminals;

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

        if (_friends.length > 0) {
            String toReturn = "BASIC|" + _id + "|" + _owner.getID() + "|" + "state" + "|" + _payments + "|" + _debt + "|"

            for (int i = 0; i < _friends.length; i++) {
                toReturn += _friends[i].getID();
                if (i + 1 != _friends.length) {
                    toReturn += ",";
                }

                return toReturn;
            }
        }
        else {
            return "BASIC|" + _id + "|" + _owner.getID() + "|" + "state" + "|" + _payments + "|" + _debt;
        }
    }
}