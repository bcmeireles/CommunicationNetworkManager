package prr.communications;

import prr.terminals.Terminal;

public abstract class InteractiveCommunication extends Communication {
    
    public InteractiveCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
        this.startInteractiveCommunication();
    }

    public void setDuration(int duration) {
        setUnits(duration);
    }

    public void startInteractiveCommunication() {
        setOnGoing(true);
        getOrigin().setCommunicating(true);
        getOrigin().setCurrentCommunication(this);
        getDestination().setCommunicating(true);
        getDestination().setCurrentCommunication(this);
        getOrigin().busy();
        getDestination().busy();        
    }

    public void endInteractiveCommunication() {
        setOnGoing(false);
        getOrigin().setCommunicating(false);
        getOrigin().setCurrentCommunication(null);
        getDestination().setCommunicating(false);
        getDestination().setCurrentCommunication(null);
        getOrigin().addDebt(getCost());
        getOrigin().setPreviousState();
        getDestination().setPreviousState();
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