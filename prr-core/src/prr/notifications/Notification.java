package prr.notifications;

import java.io.Serializable;

import prr.terminals.Terminal;

public abstract class Notification implements Serializable {
    /* Terminal that attempts the communication */
    private Terminal _terminal;

    public Notification(Terminal terminal) {
        _terminal = terminal;
    }

    public Terminal getTerminal() {
        return _terminal;
    }

}

/**
    * cli001 owns 969001
    * cli003 owns 969008
    * 
    * 969001 tenta ligar ao 969008
    * 969008 está off
    * liga 969008
    * cli001 tem O2I|969008
 */