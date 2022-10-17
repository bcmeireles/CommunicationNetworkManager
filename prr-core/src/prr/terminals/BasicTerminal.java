package prr.terminals;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String id, Client owner){
        super(id, owner);
    }

    public BasicTerminal(String id, Client owner, String state) {
        super(id, owner, state);
    }

    public boolean isAdvanced(){
        return false;
    }

}