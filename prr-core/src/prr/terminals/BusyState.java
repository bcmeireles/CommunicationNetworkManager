package prr.terminals;

import prr.terminals.Terminal;
import prr.terminals.TerminalState;

public class BusyState extends TerminalState {

    private TerminalState _previousState = null;

    public BusyState(Terminal terminal) {
        super(terminal);
    }

    public BusyState(Terminal terminal, TerminalState previous) {
        super(terminal);
        _previousState = previous;
    }

    @Override
    public TerminalState getPreviousState() {
        return _previousState;
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
    public boolean isIdle() {
        return false;
    }

    @Override
    public boolean isSilence() {
        return false;
    }

    @Override
    public boolean isBusy() {
        return true;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public String toString() {
        return "BUSY";
    }

}