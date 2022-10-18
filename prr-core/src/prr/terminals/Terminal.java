package prr.terminals;

import java.io.Serializable;

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
        private Double _payments;

        /** Terminal debt */
        private Double _debt;

        /** Terminal owner */
        private Client _owner;

        /** Terminal friends */
        private Map<String, Client> _friends;
        
        //private Map<Integrer, Notification> _notifications = new TreeMap<>();


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
                _payments = 0.0;
                _debt = 0.0;
        }

        public Terminal(String id, Client owner, String state) {
                // TODO - STATE
                _id = id;
                _owner = owner;
                _payments = 0.0;
                _debt = 0.0;
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
        }

        /**
         * Checks if this terminal can start a new communication.
         *
         * @return true if this terminal is neither off neither busy, false otherwise.
         **/
        public boolean canStartCommunication() {
                // FIXME add implementation code
        }

        public String getID() {
                return _id;
        }

        public void addFriend(Client friend) {
                _friends.put(friend.getID(), friend);
        }
}