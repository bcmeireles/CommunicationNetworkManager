package prr.communications;

import prr.terminals.Terminal;

public class VideoCommunication extends Communication {

    /** Communication duration */
    private int _duration;

    public class VideoCommunication(int id, Terminal origin, Terminal destination) {
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