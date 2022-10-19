package prr.app.terminals;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.exceptions.*;
import prr.exceptions.*;


//FIXME add mode import if needed

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

	DoOpenMenuTerminalConsole(Network receiver) {
		super(Label.OPEN_MENU_TERMINAL, receiver);
		addStringField("terminalID",Prompt.terminalKey());
	}

	@Override
	protected final void execute() throws CommandException, UnknownTerminalKeyException {
                //FIXME implement command
                // create an instance of prr.app.terminal.Menu with the
                // selected Terminal
				try {
					new prr.app.terminal.Menu(_receiver,_receiver.getTerminal(stringField("terminalID"))).open();
				} catch (prr.exceptions.UnknownTerminalKeyException e) {
					throw new prr.app.exceptions.UnknownTerminalKeyException(stringField("terminalID"));
				}
				
	}
}
