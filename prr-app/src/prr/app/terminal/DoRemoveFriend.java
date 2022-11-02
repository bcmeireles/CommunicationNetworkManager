package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Remove friend.
 */
class DoRemoveFriend extends TerminalCommand {

	DoRemoveFriend(Network context, Terminal terminal) {
		super(Label.REMOVE_FRIEND, context, terminal);
		addStringField("friendID", Prompt.terminalKey());
	}

	@Override
	protected final void execute() throws CommandException {
				try {
					_receiver.removeFriend(stringField("friendID"));
				} catch (prr.exceptions.TerminalNotInFriendsException e) {
					// DO NOTHING
				}
	}
}
