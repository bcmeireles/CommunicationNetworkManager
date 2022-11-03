package prr.app.terminal;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.*;

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

	DoStartInteractiveCommunication(Network context, Terminal terminal) {
		super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
		addStringField("destinationID", Prompt.terminalKey());
		addOptionField("commType", Prompt.commType(), "VOICE", "VIDEO");
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.startInteractiveCommunication(stringField("destinationID"), optionField("commType"), _network);
		} catch (prr.exceptions.CommunicationUnsupportedAtOriginException e) {
			_display.popup(Message.unsupportedAtOrigin(_receiver.getID(), stringField("commType")));
		} catch (prr.exceptions.CommunicationUnsupportedAtDestinationException e) {
			_display.popup(Message.unsupportedAtDestination(stringField("destinationID"), stringField("commType")));
		} catch (prr.exceptions.DestinationOffException e) {
			_display.popup(Message.destinationIsOff(stringField("destinationID")));
		} catch (prr.exceptions.DestinationBusyException e) {
			_display.popup(Message.destinationIsBusy(stringField("destinationID")));
		} catch (prr.exceptions.DestinationSilenceException e) {
			_display.popup(Message.destinationIsSilent(stringField("destinationID")));
		} catch (prr.exceptions.UnknownTerminalKeyException e) {
			throw new UnknownTerminalKeyException(stringField("destinationID"));
		}
	}
}
