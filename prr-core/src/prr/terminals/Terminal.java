package prr.terminals;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import prr.clients.Client;

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
        private Double _payments = 0.0;

        /** Terminal debt */
        private Double _debt = 0.0;

        /** Terminal owner */
        private Client _owner;

        /** Terminal friends */
        private Map<String, Terminal> _friends = new TreeMap<>();

        /**  */
        private boolean _isUnused = true;
        
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
                // FIXME add implementation code 
                return false;
        }

        /**
         * Checks if this terminal can start a new communication.
         *
         * @return true if this terminal is neither off neither busy, false otherwise.
         **/
        public boolean canStartCommunication() {
                // FIXME add implementation code
                return true;
        }

        /** Getters */

        public String getID() {
                return _id;
        }

        public Client getOwner() {
                return _owner;
        }

        public Double getPayments() {
                return _payments;
        }

        public Double getDebt() {
                return _debt;
        }

        public Map<String, Terminal> getFriends() {
                return _friends;
        }

        public void addFriend(Terminal friend) {
                _friends.put(friend.getID(), friend);
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
}