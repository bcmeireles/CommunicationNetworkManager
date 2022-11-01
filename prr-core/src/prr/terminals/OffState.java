package prr.terminals;

import prr.terminals.Terminal;
import prr.terminals.TerminalState;

public class OffState extends TerminalState {
    public OffState(Terminal terminal) {
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
        // Not possible
    }

    @Override
    public void off() {
        // Already Off
    }

    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public boolean isOff() {
        return true;
    }

    @Override
    public String toString() {
        return "OFF";
    }

}