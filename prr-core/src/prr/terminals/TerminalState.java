package prr.terminals;

import java.io.Serializable;

public abstract class TerminalState implements Serializable {
    /** Terminal */
    protected Terminal _terminal;

    /** @param terminal is the terminal */
    public TerminalState(Terminal terminal) { _terminal = terminal; }

    public abstract TerminalState getPreviousState();

    /** Behaviour for idling */
    public abstract void idle();

    /** Behaviour for silencing */
    public abstract void silence();

    /** Behaviour for busy */
    public abstract void busy();

    /** Behaviour for turning off */
    public abstract void off();

    public abstract boolean isSilence();
    public abstract boolean isBusy();
    public abstract boolean isOff();
}