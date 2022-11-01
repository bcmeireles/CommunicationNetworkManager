package prr.terminals;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import prr.clients.Client;
import prr.communications.Communication;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
public abstract class Terminal implements Serializable /* FIXME maybe addd more interfaces */{

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

        /** Terminal State */
        private TerminalState _state = new IdleState(this);

        /** Terminal id */
        private String _id;
        
        /** payments made by Terminal*/
        private double _payments = 0.0;

        /** Terminal debt */
        private double _debt = 0.0;

        /** Terminal owner */
        private Client _owner;

        /** Terminal friends */
        private Map<String, Terminal> _friends = new TreeMap<>();

        /**  */
        private boolean _isUnused = true;

        private boolean _isCommunicating = false;

        private Communication _currentCommunication = null;
        
        //private Map<Integer, Notification> _notifications = new TreeMap<>();


        public void idle() { _state.idle(); }
        public void silence() { _state.silence(); }
        public void busy() { _state.busy(); }
        public void off() { _state.off(); }

        public void setState(TerminalState state) { _state = state; }


        /**
         * @param id
         * @param owner
         */
        public Terminal(String id, Client owner) {
                _id = id;
                _owner = owner;
        }

        public Terminal(String id, Client owner, String state) {
                switch(state) {
                        case "IDLE" -> _state = new IdleState(this);
                        case "SILENT" -> _state = new SilenceState(this);
                        case "BUSY" -> _state = new BusyState(this);
                        case "OFF" -> _state = new OffState(this);
                }

                _id = id;
                _owner = owner;
        }

        // FIXME define attributes
        // FIXME define contructor(s)
        // FIXME define methods

        /**
         * Checks if this terminal can end the current interactive communication.
         *
         * @return true if this terminal is busy (i.e., it has an active interactive communication) and
         *          it was the originator of this communication.
         **/
        public boolean canEndCurrentCommunication() {

                if (_isCommunicating && _currentCommunication.getOrigin() == this)
                        return true;

                return false;
        }

        /**
         * Checks if this terminal can start a new communication.
         *
         * @return true if this terminal is neither off neither busy, false otherwise.
         **/
        public boolean canStartCommunication() {
                // PODEMOS USAR INSTANCE OF?
                //return !(_state instanceof OffState) && !(_state instanceof BusyState);

                if (!_state.isBusy() && !_state.isOff())
                        return true;

                return false;
        }

        /** Getters */

        public String getID() {
                return _id;
        }

        public Client getOwner() {
                return _owner;
        }

        public double getPayments() {
                return _payments;
        }

        public double getDebt() {
                return _debt;
        }

        public Map<String, Terminal> getFriends() {
                return _friends;
        }

        public void addFriend(Terminal friend) {
                _friends.put(friend.getID(), friend);
        }

        public boolean isFriend(Terminal friend) {
                return _friends.containsKey(friend.getID());
        }

        public void markUsed() {
                _isUnused = false;
        }

        public boolean isUnused() {
                return _isUnused;
        }

        public TerminalState getState() {
                return _state;
        }

        public boolean isCommunicating() {
                return _isCommunicating;
        }

        public void changeCommunicating() {
                _isCommunicating = !_isCommunicating;
        }

        public Communication getCurrentCommunication() {
                return _currentCommunication;
        }

        public void setCurrentCommunication(Communication communication) {
                _currentCommunication = communication;
        }
}