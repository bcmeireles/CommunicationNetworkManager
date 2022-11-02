package prr.communications;

import prr.terminals.Terminal;

public class VideoCommunication extends InteractiveCommunication {
    public VideoCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }
    
    public boolean isVideoCommunication() {
        return true;
    }

    public boolean isVoiceCommunication() {
        return false;
    }

    @Override
    public String toString() {
        return "VIDEO|" + getID() + "|" + getOrigin().getID() + "|" + getDestination().getID() + "|" + getUnits() + "|" + getCost() + "|" + (isOnGoing() ? "ONGOING" : "FINISHED");
    }
}