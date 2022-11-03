package prr.terminals;

import prr.terminals.Terminal;
import prr.terminals.TerminalState;

public class SilenceState extends TerminalState {
    public SilenceState(Terminal terminal) {
        super(terminal);
    }

    @Override
    public TerminalState getPreviousState() {
        return null;
    }

    @Override
    public void idle() {
        _terminal.setState(new IdleState(_terminal));
        
    }

    @Override
    public void silence() {
       // Already silent
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
    public boolean isIdle() {
        return false;
    }

    @Override
    public boolean isSilence() {
        return true;
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
        return "SILENCE";
    }

}