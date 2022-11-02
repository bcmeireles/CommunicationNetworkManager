package prr;

import java.io.Serializable;
import java.io.IOException;
import prr.exceptions.UnrecognizedEntryException;
import java.util.Map;
import java.util.TreeMap;

import prr.clients.Client;
import prr.terminals.Terminal;
import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;

import prr.communications.Communication;
import prr.communications.TextCommunication;
import prr.communications.InteractiveCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

import prr.exceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.lang.Integer;


// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	/** Clients */
	private final Map<String, Client> _clients = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	/** Terminals */
	private final Map<String, Terminal> _terminals = new TreeMap<>();

	/** Communications */
	private final Map<Integer, Communication> _communications = new TreeMap<>();

	/** Network object has been changed. */
	private boolean _changed = false;

	

		// FIXME define attributes
        // FIXME define contructor(s)
        // FIXME define methods

	/**
	 * Read text input file and create corresponding domain entities.
	 * 
	 * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException if there is an IO erro while processing the text file
	 */
	void importFile(String filename) throws InvalidTerminalKeyException, UnrecognizedEntryException, IOException, DuplicateClientKeyException, DuplicateTerminalKeyException, UnknownClientKeyException /* FIXME maybe other exceptions */  {
		String currentLine;
		String[] splitLine;
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while ((currentLine = reader.readLine()) != null) {
			splitLine = currentLine.split("\\|");
			
			switch (splitLine[0]) {
				case "CLIENT" -> registerClient(splitLine[1], splitLine[2], Integer.parseInt(splitLine[3]));
				case "BASIC" -> registerTerminal(splitLine[1], "BASIC", splitLine[2], splitLine[3]);
				case "FANCY" -> registerTerminal(splitLine[1], "FANCY", splitLine[2], splitLine[3]);
				case "FRIENDS" -> registerFriends(splitLine);
				default -> throw new UnrecognizedEntryException(currentLine);
			}
		}
    }

	public void registerClient(String id, String name, int taxID) throws DuplicateClientKeyException {
		if (_clients.containsKey(id)) {
			throw new DuplicateClientKeyException(id);
		}
		Client client = new Client(id, name, taxID);
		_clients.put(id, client);
		setChanged(true);
	}

	public Terminal registerTerminal(String id, String type, String clientID, String state) throws InvalidTerminalKeyException, DuplicateTerminalKeyException, UnknownClientKeyException, UnrecognizedEntryException {
		if (!id.matches("[0-9]{6}")) {
			throw new InvalidTerminalKeyException(id);
		}
		
		if (_terminals.containsKey(id)) {
			throw new DuplicateTerminalKeyException(id);
		}
		if (!_clients.containsKey(clientID)) {
			throw new UnknownClientKeyException(clientID);
		}

		Terminal terminal;
		Client client = _clients.get(clientID);

		switch (state) {
			case "SILENCE" -> state = "SILENT";
			case "ON" -> state = "IDLE";
		}

		switch (type) {
			case "BASIC" -> terminal = new BasicTerminal(id, client, state);
			case "FANCY" -> terminal = new FancyTerminal(id, client, state);
			default -> throw new UnrecognizedEntryException(type);
		}
		
		_terminals.put(id, terminal);
		client.addTerminal(terminal);
		setChanged(true);
		return terminal;

	}
	
	//public BasicTerminal registerBasicTerminal(String id, String clientID, String state) throws DuplicateTerminalKeyException, UnknownClientKeyException, UnrecognizedEntryException {
	//	if (id.length() != 6) {
	//		throw new UnrecognizedEntryException(id);
	//	}
	//	
	//	if (_terminals.containsKey(id)) {
	//		throw new DuplicateTerminalKeyException(id);
	//	}
//
	//	if(!_clients.containsKey(clientID)) {
	//		throw new UnknownClientKeyException(clientID);
	//	}
//
	//	Client client = _clients.get(clientID);
	//	BasicTerminal terminal = new BasicTerminal(id, client, state);
	//	_terminals.put(id, terminal);
	//	client.addTerminal(terminal);
	//	setChanged(true);
	//	return terminal;
	//}
//
	//public FancyTerminal registerFancyTerminal(String id, String clientID, String state) throws DuplicateTerminalKeyException, UnknownClientKeyException, UnrecognizedEntryException {
	//	if (id.length() != 6) {
	//		throw new UnrecognizedEntryException(id);
	//	}
	//	
	//	if (_terminals.containsKey(id)) {
	//		throw new DuplicateTerminalKeyException(id);
	//	}
//
	//	if(!_clients.containsKey(clientID)) {
	//		throw new UnknownClientKeyException(clientID);
	//	}
//
	//	Client client = _clients.get(clientID);
	//	FancyTerminal terminal = new FancyTerminal(id, client, state);
	//	_terminals.put(id, terminal);
	//	client.addTerminal(terminal);
	//	setChanged(true);
	//	return terminal;
	//}

	public void registerFriends(String[] split) throws UnrecognizedEntryException {
		if (split.length != 3) {
			throw new UnrecognizedEntryException(split[0]);
		}

		if (!_terminals.containsKey(split[1])) {
			throw new UnrecognizedEntryException(split[1]);
		}

		Terminal friender = _terminals.get(split[1]);

		String[] toAdd = split[2].split("\\,");

		for (int i = 0; i < toAdd.length; i++) {
			if (!_terminals.containsKey(toAdd[i])) {
				throw new UnrecognizedEntryException(split[0]);
			}

			friender.addFriend(_terminals.get(toAdd[i]));
		}

		setChanged(true);
	}

	public String showClient(String id) throws UnknownClientKeyException {
		if (!_clients.containsKey(id)) {
			throw new UnknownClientKeyException(id);
		}
		
		Client client = _clients.get(id);

		return client.toString();
	}

	public void enableNotifications(String id) throws NotificationsAlreadyEnabledException {
		Client client = _clients.get(id);
		try {
			client.enableNotifications();
		} catch (NotificationsAlreadyEnabledException e) {
			throw new NotificationsAlreadyEnabledException();
		}
	}

	public void disableNotifications(String id) throws NotificationsAlreadyDisabledException {
		Client client = _clients.get(id);
		try {
			client.disableNotifications();
		} catch (NotificationsAlreadyDisabledException e) {
			throw new NotificationsAlreadyDisabledException();
		}
	}

	public String getAllClients() {
		if (_clients.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Client client : _clients.values()) {
			sb.append(client.toString() + "\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public Terminal getTerminal(String id) throws UnknownTerminalKeyException {
		if (!_terminals.containsKey(id)) {
			throw new UnknownTerminalKeyException(id);
		}

		return _terminals.get(id);
	}

	public String getAllTerminals() {
		if (_terminals.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Terminal terminal : _terminals.values()) {
			sb.append(terminal.toString() + "\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();

	}

	public String getUnusedTerminals() {
		if (_terminals.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Terminal terminal : _terminals.values()) {
			if (terminal.isUnused()) {
				sb.append(terminal.toString() + "\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();

	}
	
	public TextCommunication startTextCommunication(Terminal origin, Terminal destination, String text) throws DestinationOffException {
		int id = _communications.size() + 1;

		if (destination.getState().isOff()) {
			throw new DestinationOffException();
		}

		TextCommunication textCom = new TextCommunication(id, origin, destination, text);
		
		textCom.setUnits(text.length());

		long cost = textCom.calculateCost();
		textCom.setCost(cost);
		origin.addDebt(cost);

		_communications.put(id, textCom);
		
		setChanged(true);

		return textCom;
	}

	public InteractiveCommunication startInteractiveCommunication(String type, Terminal origin, Terminal destination) throws CommunicationUnsupportedAtOriginException, CommunicationUnsupportedAtDestinationException, DestinationOffException, DestinationBusyException, DestinationSilenceException {		
		int id = _communications.size() + 1;

		if (destination.getState().isBusy()) {
			throw new DestinationBusyException();
		}

		if (destination.getState().isOff()) {
			throw new DestinationOffException();
		}

		if (destination.getState().isSilence()) {
			throw new DestinationSilenceException();
		}

		InteractiveCommunication newCom = null;

		if (type.equals("VOICE")) {
			newCom = new VoiceCommunication(id, origin, destination);
		}

		if (type.equals("VIDEO")) {

			if (!origin.isFancy())
				throw new CommunicationUnsupportedAtOriginException();
			if (!destination.isFancy())
				throw new CommunicationUnsupportedAtDestinationException();

			newCom = new VideoCommunication(id, origin, destination);
		}

		newCom.startInteractiveCommunication();

		_communications.put(id, newCom);

		setChanged(true);

		return newCom;
	}

	public long getCommunicationCost(Integer communicationID) {
		return _communications.get(communicationID).getCost();
	}

	/** 
	* public String showStartedCommunications(String clientID) {
	* 	StringBuilder sb = new StringBuilder();
	* 	for (Communication communication : _communications.values()) {
	* 		if (communication.getOrigin().getClient().equals(client)) {
	* 			sb.append(communication.toString() + "\n");
	* 		}
	* 	}
	* 	sb.deleteCharAt(sb.length() - 1);
	* 	return sb.toString();
	* }
	* 
	* public String showReceivedCommunications(String clientID) {
	* 	StringBuilder sb = new StringBuilder();
	* 	for (Communication communication : _communications.values()) {
	* 		if (communication.getDestination().getClient().getID().equals(client)) {
	* 			sb.append(communication.toString() + "\n");
	* 		}
	* 	}
	* 	sb.deleteCharAt(sb.length() - 1);
	* 	return sb.toString();
	* }
	* 
	*/

	public void addFriend(Terminal friender, String friendID) throws UnknownTerminalKeyException, TerminalCannotAddItselfException {

		Terminal friend = _terminals.get(friendID);

		if (friender.equals(friend)) {
			throw new TerminalCannotAddItselfException();
		}

		friender.addFriend(friend);
		setChanged(true);
	}

	public void removeFriend(String frienderID, String friendID) {
		Terminal friender = _terminals.get(frienderID);
		Terminal friend = _terminals.get(friendID);

		friender.removeFriend(friend);
		setChanged(true);
	}

	public void setChanged(boolean changed) {
		_changed = changed;
	}

	public boolean hasChanged() {
		return _changed;
	}

	public String getAllCommunications() {
		if (_communications.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Communication communication : _communications.values()) {
			sb.append(communication.toString() + "\n");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public String showClientsWithDebts() {
		if (_clients.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Client client : _clients.values()) {
			if (client.getDebt() > 0) {
				sb.append(client.toString() + "\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
	
	public String showClientsWithoutDebts() {
		if (_clients.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Client client : _clients.values()) {
			if (client.getDebt() == 0) {
				sb.append(client.toString() + "\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public String showTerminalsWithPositiveBalance() {
		if (_terminals.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Terminal terminal : _terminals.values()) {
			if (terminal.getBalance() > 0) {
				sb.append(terminal.toString() + "\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public String showCommunicationsToClient(String clientID) {
		if (_communications.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Communication communication : _communications.values()) {
			if (communication.getDestination().getOwner().getID().equals(clientID)) {
				sb.append(communication.toString() + "\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public String showCommunicationsFromClient(String clientID) {
		if (_communications.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Communication communication : _communications.values()) {
			if (communication.getOrigin().getOwner().getID().equals(clientID)) {
				sb.append(communication.toString() + "\n");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}