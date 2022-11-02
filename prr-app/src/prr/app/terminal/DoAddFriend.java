package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.exceptions.*;
import prr.exceptions.*;

//FIXME add more imports if needed

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

	DoAddFriend(Network context, Terminal terminal) {
		super(Label.ADD_FRIEND, context, terminal);
		addStringField("friendID", Prompt.terminalKey());
	}

	@Override
	protected final void execute() throws CommandException {
                //FIXME implement command
				try {
					_receiver.addFriend(stringField("friendID"), _network);
				} catch (prr.exceptions.UnknownTerminalKeyException e) {
					// DO NOTHING
				}

				
	}
}
