public class IdleState extends TerminalState {
    public IdleState(Terminal terminal) {
        super(terminal);
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
        _terminal.setState(new BusyState(_terminal));

    }

    @Override
    public void off() {
        _terminal.setState(new OffState(_terminal));

    }

}