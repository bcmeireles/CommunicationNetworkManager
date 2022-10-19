package prr.terminals;

import prr.terminals.Terminal;
import prr.terminals.TerminalState;

public class BusyState extends TerminalState {
    public BusyState(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void idle() {
        _terminal.setState(new IdleState(_terminal));
        
    }

    @Override
    public void silence() {
        _terminal.setState(new SilenceState(_terminal));

    }

    @Override
    public void busy() {
        // Already busy

    }

    @Override
    public void off() {
        // Not possible

    }

    @Override
    public String toString() {
        return "BUSY";
    }

}