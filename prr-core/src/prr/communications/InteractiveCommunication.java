package prr.communications;

import prr.terminals.Terminal;

public abstract class InteractiveCommunication extends Communication {
    
    public InteractiveCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    public void setDuration(int duration) {
        setUnits(duration);
    }

    public double calculateCost() {
        return getOrigin().getOwner().getLevel().getVideoCost(getUnits());        
    }
}