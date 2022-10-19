package prr.clients;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import prr.terminals.Terminal;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

public class Client implements Serializable {

    /** Client ID */
    private String _id;

    /** Client tax ID */
    private int _taxID;

    /** Client name */
    private String _name;

    /** Client balance */
    private Double _balance;

    /** Payments made by client */
    private Double _payments;

    /** Debts client has */
    private Double _debts;

    /** Consequent text messages */
    private int _textStreak;

    /** Consequent video calls */
    private int _videoStreak;

    /** Terminals owned by client */
    private Map<String, Terminal> _terminals = new TreeMap<>();

    /** Client Level */
    private ClientLevel _level = new NormalClient(this);
    
    /** Client tariff */
    // private Tariff _tariff;

    /** Client notifications */
    //private Map<Integrer, Notification> _notifications = new TreeMap<>();

    /**
     * @param id
     * @param taxID
     * @param name
     */
    public Client(String id, String name, int taxID) {
        
        _id = id;
        _taxID = taxID;
        _name = name;
        _balance = 0.0;
        _textStreak = 0;
        _videoStreak = 0;
        _payments = 0.0;
        _debts = 0.0;
    }

    /** Getters */

    public String getID() {
        return _id;
    }

    public int getTaxID() {
        return _taxID;
    }

    public String getName() {
        return _name;
    }

    public Double getPayments() {
        return _payments;
    }

    public Double getDebts() {
        return _debts;
    }


    public void addTerminal(Terminal terminal) {
        _terminals.put(terminal.getID(), terminal);
    }

    public void setLevel(ClientLevel level) {
        _level = level;
    }
}