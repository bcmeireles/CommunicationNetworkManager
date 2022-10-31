package prr.communications;

import prr.terminals.Terminal;

public class VoiceCommunication extends Communication {

    /** Communication duration */
    private int _duration;

    public class VoiceCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    public int getDuration() {
        return _duration;
    }

    public void setDuration(int duration) {
        _duration = duration;
    }

    public double calculateCost(Client client) {
        // TODO
    }
}