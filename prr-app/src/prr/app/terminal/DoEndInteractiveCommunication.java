package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

import prr.app.exceptions.*;
import prr.exceptions.*;

/**
 * Command for ending communication.
 */
class DoEndInteractiveCommunication extends TerminalCommand {

	DoEndInteractiveCommunication(Network context, Terminal terminal) {
		super(Label.END_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canEndCurrentCommunication());
		addStringField("duration", Prompt.duration());
	}

	@Override
	protected final void execute() throws CommandException {
		_display.popup(Message.communicationCost(_receiver.calculateCommunicationCost(stringField("duration"))));
		_receiver.endCurrentCommunication(stringField("duration"));
	}
}
