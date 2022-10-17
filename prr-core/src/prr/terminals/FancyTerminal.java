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
    public boolean isAdvanced(){
        return true;
    }

}