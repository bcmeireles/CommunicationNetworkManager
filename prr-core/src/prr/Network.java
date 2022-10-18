package prr;

import java.io.Serializable;
import java.io.IOException;
import prr.exceptions.UnrecognizedEntryException;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	/** Clients */
	private final Map<String, Client> _clients = new TreeMap<>();

	/** Terminals */
	private final Map<String, Terminal> _terminals = new TreeMap<>();

	

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
	void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
		String currentLine;
		String[] splitLine;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while ((currentLine = reader.readLine()) != null) {
				splitLine = currentLine.split("\\|");
				
				switch (splitLine[0]) {
					case "CLIENT" -> registerClient(splitLine[1], splitLine[2], splitLine[3]);
					case "BASIC" -> registerBasicTerminal(splitLine[1], splitLine[2], splitLine[3]);
					case "FANCY" -> registerFancyTerminal(splitLine[1], splitLine[2], splitLine[3]);
					case "FRIENDS" -> registerFriends(splitLine);
					default -> throw new UnrecognizedEntryException(currentLine);
				}
			}
		}
    }

	public Client registerClient(String id, String name, int taxID) throws DuplicateClientKeyException {
		if (_clients.containsKey(id)) {
			throw new DuplicateClientKeyException(id);
		}
		Client client = new Client(id, name, taxID);
		_clients.put(id, client);
		return client;
	}
	
	public BasicTerminal registerBasicTerminal(String id, String clientID, String state) throws DuplicateTerminalKeyException, UnknownClientKeyException {
		if (id.lenght != 6) {
			throw new UnrecognizedEntryException(split);
		}
		
		if (_terminals.containsKey(id)) {
			throw new DuplicateTerminalKeyException(id);
		}

		if(!_clients.containsKey(clientID)) {
			throw new UnknownClientKeyException(clientID);
		}

		client = _clients.get(clientID);
		BasicTerminal terminal = new BasicTerminal(id, client, state);
		_terminals.put(id, terminal);
		return terminal;
	}

	public FancyTerminal registerFancyTerminal(String id, String clientID, String state) throws DuplicateTerminalKeyException, UnknownClientKeyException {
		if (id.lenght != 6) {
			throw new UnrecognizedEntryException(split);
		}
		
		if (_terminals.containsKey(id)) {
			throw new DuplicateTerminalKeyException(id);
		}

		if(!_clients.containsKey(clientID)) {
			throw new UnknownClientKeyException(clientID);
		}

		client = _clients.get(clientID);
		FancyTerminal terminal = new FancyTerminal(id, client, state);
		_terminals.put(id, terminal);
		return terminal;
	}

	public void importFriends(String[] split) throws UnrecognizedEntryException {
		if (split.length != 3) {
			throw new UnrecognizedEntryException(split);
		}

		if (!_terminals.containsKey(split[1])) {
			throw new UnrecognizedEntryException(split);
		}

		friender = _terminals.get(split[1]);

		String[] toAdd = split[2].split("\\,");

		for (int i = 0; i < toAdd.length; i++) {
			if (!_terminals.containsKey(toAdd[i])) {
				throw new UnrecognizedEntryException(split);
			}

			friender.addFriend(_terminals.get(toAdd[i]));
		}
	}

	public Terminal getTerminal(String id) throws UnknownTerminalKeyException {
		if (!_terminals.containsKey(id)) {
			throw new UnknownTerminalKeyException(id);
		}

		return _terminals.get(id);
	}
}



	
