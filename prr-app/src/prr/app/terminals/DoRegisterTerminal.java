package prr.app.terminals;

import prr.Network;
// import prr.app.exceptions.DuplicateTerminalKeyException;
// import prr.app.exceptions.InvalidTerminalKeyException;
// import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

	DoRegisterTerminal(Network receiver) {
		super(Label.REGISTER_TERMINAL, receiver);
		//FIXME add command fields
		addStringField("terminalID", Prompt.terminalKey());
		addStringField("clientID", Prompt.clientKey());
		addOptionField("terminalType", Prompt.terminalType(), "BASIC", "FANCY");
	}

	@Override
	protected final void execute() throws CommandException {
			//try {
				_receiver.registerTerminal(
					stringField("terminalType"),
					stringField("terminalID"),
					stringField("clientID"),
					"IDLE"
				);
			//} //catch (DuplicateTerminalKeyException e) {
			//	throw new DuplicateTerminalKeyException(stringField("terminalID"));
			//} catch (UnknownClientKeyException e) {
			//	throw new UnknownClientKeyException(stringField("clientID"));
			//}
	}
}