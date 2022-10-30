package prr.communications;

import prr.terminals.Terminal;

public class VoiceCommunication extends Communication {

    /** Communication duration */
    private double _duration;

    public class VoiceCommunication(int id, Terminal origin, Terminal destination) {
        super(id, origin, destination);
    }

    public double getDuration() {
        return _duration;
    }

    public void setDuration(double duration) {
        _duration = duration;
    }

    public double calculateCost(Client client) {
        return _duration * client.getVoiceCost();
    }
}