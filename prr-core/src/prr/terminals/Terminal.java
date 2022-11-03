package prr.terminals;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

import prr.Network;

import prr.clients.Client;
import prr.communications.Communication;
import prr.communications.InteractiveCommunication;

import java.lang.Integer;

import prr.exceptions.*;

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
        private long _payments = 0;

        /** Terminal debt */
        private long _debt = 0;

        /** Terminal balance */
        private long _balance = 0;

        /** Terminal owner */
        private Client _owner;

        /** Terminal friends */
        private Map<String, Terminal> _friends = new TreeMap<>();

        /**  */
        private boolean _isUnused = true;

        private boolean _isCommunicating = false;

        private InteractiveCommunication _currentCommunication = null;
        
        //private Map<Integer, Notification> _notifications = new TreeMap<>();

        private ArrayList<Terminal> _communicationAttemptsOff = new ArrayList<Terminal>();
        private ArrayList<Terminal> _communicationAttemptsSilence = new ArrayList<Terminal>();
        private ArrayList<Terminal> _communicationAttemptsBusy = new ArrayList<Terminal>();

        // private ArrayList<Notification> _pendingNotifications = new ArrayList<Notification>();


        public void idle() { _state.idle(); }
        public void silence() { _state.silence(); }
        public void busy() { _state.busy(); }
        public void off() { _state.off(); }

        public void setState(TerminalState state) { _state = state; }

        public void setPreviousState() {
                if (_state.isBusy()) {
                        _state = _state.getPreviousState();
                }
        }

        public abstract boolean isFancy();

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

        public void addPayment(long payment) {
                _payments += payment;
                _balance = _payments - _debt;
                _owner.addPayment(payment);
        }

        public long getPayments() {
                return _payments;
        }

        public void addDebt(long debt) {
                _debt += debt;
                _balance = _payments - _debt;
                _owner.addDebt(debt);
        }

        public long getDebt() {
                return _debt;
        }

        public long getBalance() {
                return _balance;
        }

        public Map<String, Terminal> getFriends() {
                return _friends;
        }

        public void addFriend(Terminal friend) {
                _friends.put(friend.getID(), friend);
        }

        public void addFriend(String friendID, Network network) throws UnknownTerminalKeyException, TerminalCannotAddItselfException {
                try {
                        network.addFriend(this, friendID);
                } catch (UnknownTerminalKeyException | TerminalCannotAddItselfException e) {
                        throw e;
                }
        }

        public void removeFriend(Terminal friend) {
                _friends.remove(friend.getID());
        }

        public void removeFriend(String friendID) throws TerminalNotInFriendsException {
                try {
                        _friends.remove(friendID);
                } catch (ClassCastException e) {
                        throw new TerminalNotInFriendsException();
                }
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

        public void setCommunicating(boolean communicating) {
                _isCommunicating = communicating;
        }

        public InteractiveCommunication getCurrentCommunication() {
                return _currentCommunication;
        }

        public void setCurrentCommunication(InteractiveCommunication communication) {
                _currentCommunication = communication;
        }

        public long calculateCommunicationCost(String units) {
                return _currentCommunication.calculateCost(Integer.parseInt(units));
        }

        public void startTextCommunication(String destinationID, String message, Network network) throws UnknownTerminalKeyException, DestinationOffException {
                try {
                        network.startTextCommunication(this, network.getTerminal(destinationID), message);
                } catch (UnknownTerminalKeyException e) {
                        throw e;
                } catch (DestinationOffException e) {
                        //addCommunicationAttempt(network.getTerminal(destinationID));
                        ((Terminal) network.getTerminal(destinationID)).addCommunicationAttemptOff(this);
                        throw e;
                }
        }

        public void startInteractiveCommunication(String destinationID, String type, Network network) throws UnknownTerminalKeyException, CommunicationUnsupportedAtOriginException, CommunicationUnsupportedAtDestinationException, DestinationOffException, DestinationBusyException, DestinationSilenceException {
                try {
                        network.startInteractiveCommunication(type, this, network.getTerminal(destinationID));
                } catch (UnknownTerminalKeyException | CommunicationUnsupportedAtOriginException | CommunicationUnsupportedAtDestinationException e) {
                        throw e;
                } catch (DestinationOffException e) {
                        ((Terminal) network.getTerminal(destinationID)).addCommunicationAttemptOff(this);
                        throw e;
                } catch (DestinationBusyException e) {
                        ((Terminal) network.getTerminal(destinationID)).addCommunicationAttemptBusy(this);
                        throw e;
                } catch (DestinationSilenceException e) {
                        ((Terminal) network.getTerminal(destinationID)).addCommunicationAttemptSilence(this);
                        throw e;
                }
        }

        public void endCurrentCommunication(String duration) {
                _currentCommunication.setUnits(Integer.parseInt(duration));
                _currentCommunication.setCost(_currentCommunication.calculateCost());
                _currentCommunication.endInteractiveCommunication();
        }

        public String showCurrentCommunication() throws NoCurrentCommunicationException {
                if (!_isCommunicating)
                        throw new NoCurrentCommunicationException();
                return _currentCommunication.toString();
        }

        public void doPayment(String commID, Network network) throws InvalidCommunicationKeyException, ClientNotCommunicationOwnerException, CommunicationAlreadyPaidException {
                try {
                        network.doPayment(this, commID);
                } catch (InvalidCommunicationKeyException | ClientNotCommunicationOwnerException | CommunicationAlreadyPaidException e) {
                        throw e;
                }
        }

        public void addCommunicationAttemptOff(Terminal terminal) {
                _communicationAttemptsOff.add(terminal);
        }

        public void addCommunicationAttemptBusy(Terminal terminal) {
                _communicationAttemptsBusy.add(terminal);
        }

        public void addCommunicationAttemptSilence(Terminal terminal) {
                _communicationAttemptsSilence.add(terminal);
        }

        public ArrayList<Terminal> getCommunicationAttemptsOff() {
                return _communicationAttemptsOff;
        }

        public ArrayList<Terminal> getCommunicationAttemptsBusy() {
                return _communicationAttemptsBusy;
        }

        public ArrayList<Terminal> getCommunicationAttemptsSilence() {
                return _communicationAttemptsSilence;
        }


        @Override
        public boolean equals(Object o) {
                if (o instanceof Terminal) {
                        Terminal t = (Terminal) o;
                        return t.getID().equals(this.getID());
                }
                return false;
        }

}