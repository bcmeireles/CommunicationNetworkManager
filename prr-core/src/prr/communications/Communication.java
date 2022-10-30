package prr.communications;

import java.io.Serializable;

import prr.terminals.Terminal;

public abstract Communication implements Serializable {

    /** Communication ID */
    private int _id;

    /** Origin terminal */
    private Terminal _origin;

    /** Destination terminal */
    private Terminal _destination;

    /** Communication cost */
    private double _cost = 0.0;

    public Communication(int id, Terminal origin, Terminal destination) {
        _id = id;
        _origin = origin;
        _destination = destination;
    }

    public int getId() {
        return _id;
    }

    public Terminal getOrigin() {
        return _origin;
    }

    public Terminal getDestination() {
        return _destination;
    }

    public double getCost() {
        return _cost;
    }

    public void setCost(double cost) {
        _cost = cost;
    }
}