package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.*;

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

	DoPerformPayment(Network context, Terminal terminal) {
		super(Label.PERFORM_PAYMENT, context, terminal);
		addStringField("commID", Prompt.commKey());
	}

	@Override
	protected final void execute() throws CommandException {
                try {
					_receiver.doPayment(stringField("commID"), _network);
				} catch (prr.exceptions.InvalidCommunicationKeyException | prr.exceptions.ClientNotCommunicationOwnerException e) {
					_display.popup(Message.invalidCommunication());
				}
	}
}
