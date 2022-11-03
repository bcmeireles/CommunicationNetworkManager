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