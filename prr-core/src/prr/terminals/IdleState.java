package prr.terminals;

import prr.terminals.Terminal;
import prr.terminals.TerminalState;


public class IdleState extends TerminalState {
    public IdleState(Terminal terminal) {
        super(terminal);
    }

    @Override
    public TerminalState getPreviousState() {
        return null;
    }

    @Override
    public void idle() {
        // Already idle
        
    }

    @Override
    public void silence() {
        _terminal.setState(new SilenceState(_terminal));

    }

    @Override
    public void busy() {
        _terminal.setState(new BusyState(_terminal, this));

    }

    @Override
    public void off() {
        _terminal.setState(new OffState(_terminal));

    }

    @Override
    public boolean isSilence() {
        return false;
    }

    @Override
    public boolean isBusy() {
        return false;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public String toString() {
        return "IDLE";
    }

}