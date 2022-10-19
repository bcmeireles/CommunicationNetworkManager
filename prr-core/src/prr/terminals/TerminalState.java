package prr.terminals;

public abstract class TerminalState {
    /** Terminal */
    protected Terminal _terminal;

    /** @param terminal is the terminal */
    public TerminalState(Terminal terminal) { _terminal = terminal; }

    /** Behaviour for idling */
    public abstract void idle();

    /** Behaviour for silencing */
    public abstract void silence();

    /** Behaviour for busy */
    public abstract void busy();

    /** Behaviour for turning off */
    public abstract void off();
}