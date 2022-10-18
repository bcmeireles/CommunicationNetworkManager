package prr.terminals;

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

        if (_friends.length > 0) {
            String toReturn = "FANCY|" + _id + "|" + _owner.getID() + "|" + "state" + "|" + _payments + "|" + _debt + "|"

            for (int i = 0; i < _friends.length; i++) {
                toReturn += _friends[i].getID();
                if (i + 1 != _friends.length) {
                    toReturn += ",";
                }

                return toReturn;
            }
        }
        else {
            return "FANCY|" + _id + "|" + _owner.getID() + "|" + "state" + "|" + _payments + "|" + _debt;
        }
    }
}