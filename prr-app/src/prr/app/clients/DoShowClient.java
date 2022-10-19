package prr.app.clients;

import prr.Network;

import prr.app.exceptions.*;
import prr.exceptions.*;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

	DoShowClient(Network receiver) {
		super(Label.SHOW_CLIENT, receiver);
		//FIXME add command fields
		addStringField("id", Prompt.key());
	}

	@Override
	protected final void execute() throws CommandException {
		try  {
			_display.popup(_receiver.showClient(stringField("id")));
		} catch (prr.exceptions.UnknownClientKeyException e) {
			throw new prr.app.exceptions.UnknownClientKeyException(stringField("id"));
		}
				
	}
}
