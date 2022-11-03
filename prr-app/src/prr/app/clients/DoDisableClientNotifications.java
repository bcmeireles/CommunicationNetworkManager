package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.*;

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

	DoDisableClientNotifications(Network receiver) {
		super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
		addStringField("id", Prompt.key());
	}

	@Override
	protected final void execute() throws CommandException {
        try {
			_receiver.disableNotifications(stringField("id"));
		} catch (prr.exceptions.NotificationsAlreadyDisabledException e) {
			_display.popup(Message.clientNotificationsAlreadyDisabled());
		}
	}
}
