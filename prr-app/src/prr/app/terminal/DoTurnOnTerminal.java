package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.TerminalAlreadyIdleException;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

	DoTurnOnTerminal(Network context, Terminal terminal) {
		super(Label.POWER_ON, context, terminal);
	}

	@Override
	protected final void execute() throws CommandException {
        try {
			_network.idleTerminal(_receiver);
		} catch (prr.exceptions.TerminalAlreadyIdleException e) {
			_display.popup(Message.alreadyOn());
		}
	}
}
