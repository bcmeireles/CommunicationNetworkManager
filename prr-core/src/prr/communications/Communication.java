package prr.communications;

import java.io.Serializable;

import prr.terminals.Terminal;

public abstract class Communication implements Serializable {

    /** Communication ID */
    private int _id;

    /** Origin terminal */
    private Terminal _origin;

    /** Destination terminal */
    private Terminal _destination;

    /** Communication cost */
    private long _cost = 0;

    /** Message length for Text Communications and duration for Voice/Video */
    private int _units = 0;

    /** Friendship status between origin and destination */
    private boolean _friendshipStatus = false;

    private boolean _onGoing = false;

    public Communication(int id, Terminal origin, Terminal destination) {
        _id = id;
        _origin = origin;
        _destination = destination;
    }

    public int getID() {
        return _id;
    }

    public Terminal getOrigin() {
        return _origin;
    }

    public Terminal getDestination() {
        return _destination;
    }

    public long getCost() {
        return _cost;
    }

    public void setCost(long cost) {
        if (getFriendshipStatus()) {
            _cost = Math.round(cost / 2);
        } else {
            _cost = cost;
        }
    }

    public int getUnits() {
        return _units;
    }

    public void setUnits(int units) {
        _units = units;
    }

    public void setFriendshipStatus(boolean friendshipStatus) {
        _friendshipStatus = friendshipStatus;
    }

    public boolean getFriendshipStatus() {
        return _friendshipStatus;
    }

    public boolean isOnGoing() {
        return _onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        _onGoing = onGoing;
    }
    

    public abstract long calculateCost();
    public abstract long calculateCost(int duration);

}