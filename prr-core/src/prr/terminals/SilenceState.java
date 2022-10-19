package prr.terminals;

import prr.terminals.Terminal;
import prr.terminals.TerminalState;

public class SilenceState extends TerminalState {
    public SilenceState(Terminal terminal) {
        super(terminal);
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
        _terminal.setState(new BusyState(_terminal));

    }

    @Override
    public void off() {
        _terminal.setState(new OffState(_terminal));

    }

}