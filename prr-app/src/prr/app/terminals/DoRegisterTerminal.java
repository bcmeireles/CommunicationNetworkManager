package prr.app.terminals;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.exceptions.*;
import prr.exceptions.*;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

	DoRegisterTerminal(Network receiver) {
		super(Label.REGISTER_TERMINAL, receiver);
		addStringField("terminalID", Prompt.terminalKey());
		addOptionField("terminalType", Prompt.terminalType(), "BASIC", "FANCY");
		addStringField("clientID", Prompt.clientKey());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.registerTerminal(
				stringField("terminalID"),
				stringField("terminalType"),
				stringField("clientID"),
				"IDLE"
			);
		} catch (prr.exceptions.DuplicateTerminalKeyException e) {
			throw new prr.app.exceptions.DuplicateTerminalKeyException(stringField("terminalID"));
		} catch (prr.exceptions.InvalidTerminalKeyException e) {
			throw new prr.app.exceptions.InvalidTerminalKeyException(stringField("terminalID"));
		} catch (prr.exceptions.UnknownClientKeyException e) {
			throw new prr.app.exceptions.UnknownClientKeyException(stringField("clientID"));
		} catch (prr.exceptions.UnrecognizedEntryException e) {
			e.printStackTrace();
		}
	}
}
