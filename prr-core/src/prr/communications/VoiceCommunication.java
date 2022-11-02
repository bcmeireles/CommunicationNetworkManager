package prr.communications;

import prr.terminals.Terminal;

public class VoiceCommunication extends InteractiveCommunication {
    public VoiceCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }
    
    public boolean isVoiceCommunication() {
        return true;
    }

    public boolean isVideoCommunication() {
        return false;
    }

    @Override
    public String toString() {
        return "VOICE|" + getID() + "|" + getOrigin().getID() + "|" + getDestination().getID() + "|" + getUnits() + "|" + getCost() + "|" + (isOnGoing() ? "ONGOING" : "FINISHED");
    }
}