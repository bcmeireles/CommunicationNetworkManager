package prr.communications;

import prr.terminals.Terminal;

public abstract class InteractiveCommunication extends Communication {
    
    public InteractiveCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
        this.startCommunication();
    }

    public void setDuration(int duration) {
        setUnits(duration);
    }

    @Override
    public long calculateCost() {
        return getOrigin().getOwner().getLevel().getVideoCost(getUnits());        
    }


    @Override
    public long calculateCost(int duration) {
        return getOrigin().getOwner().getLevel().getVideoCost(duration);        
    }
}