package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.TerminalAlreadySilencedException;

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

	DoSilenceTerminal(Network context, Terminal terminal) {
		super(Label.MUTE_TERMINAL, context, terminal);
	}

	@Override
	protected final void execute() throws CommandException {
                try {
					_network.silenceTerminal(_receiver);
				} catch (prr.exceptions.TerminalAlreadySilencedException e) {
					_display.popup(Message.alreadySilent());
				}
	}
}
