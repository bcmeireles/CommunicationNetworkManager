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
}