package prr.clients;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import prr.terminals.Terminal;

import prr.tariffs.Tariff;
import prr.tariffs.BaseTariff;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

public class Client implements Serializable {

    /** Client ID */
    private String _id;

    /** Client tax ID */
    private int _taxID;

    /** Client name */
    private String _name;

    /** Client balance */
    private long _balance = 0;

    /** Payments made by client */
    private long _payments = 0;

    /** Debts client has */
    private long _debts = 0;

    /** Has notifications enabled */
    private boolean _notificationEnabled = true;

    /** Number of text communications in a row */
    private int _textStreak = 0;

    /** Number of video communications in a row */
    private int _videoStreak = 0;

    /** Terminals owned by client */
    private Map<String, Terminal> _terminals = new TreeMap<>();

    /** Client Level */
    private ClientLevel _level = new NormalClient(this);

    private Tariff _tariff = new BaseTariff();
    
    /** Client tariff */
    // private Tariff _tariff;

    /** Client notifications */
    //private Map<Integer, Notification> _notifications = new TreeMap<>();

    /**
     * @param id
     * @param taxID
     * @param name
     */
    public Client(String id, String name, int taxID) {
        
        _id = id;
        _taxID = taxID;
        _name = name;
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

    public long getBalance() {
        return _balance;
    }

    public long getPayments() {
        return _payments;
    }

    public long getDebts() {
        return _debts;
    }

    public int getTextStreak() {
        return _textStreak;
    }

    public int getVideoStreak() {
        return _videoStreak;
    }

    public void addTerminal(Terminal terminal) {
        _terminals.put(terminal.getID(), terminal);
    }

    public void setLevel(ClientLevel level) {
        _level = level;
    }

    public boolean notificatonsEnabled() {
        return _notificationEnabled;
    }

    public Integer terminalCount() {
        return _terminals.size();
    }

    public void addTextStreak() {
        _textStreak += 1;
    }

    public void resetTextStreak() {
        _textStreak = 0;
    }

    public void addVideoStreak() {
        _videoStreak += 1;
    }

    public void resetVideoStreak() {
        _videoStreak = 0;
    }

    public ClientLevel getLevel() {
        return _level;
    }

    public Tariff getTariff() {
        return _tariff;
    }
 

    @Override
    public String toString() {
        String toReturn = "CLIENT|" + _id + "|" + _name + "|" + _taxID + "|" + _level.toString() + "|";
        if (notificatonsEnabled()) {
            toReturn += "YES|";
        } else {
            toReturn += "NO|";
        }
        if (terminalCount() == 0) {
            toReturn += "0|0|0";
        }
        else {
            toReturn += terminalCount() + "|" + Math.round(_payments) + "|" + Math.round(_debts);
        }

        return toReturn;
    }
}