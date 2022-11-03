package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.*;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

	DoEnableClientNotifications(Network receiver) {
		super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
		addStringField("id", Prompt.key());
	}

	@Override
	protected final void execute() throws CommandException {
		try {
			_receiver.enableNotifications(stringField("id"));
		} catch (prr.exceptions.NotificationsAlreadyEnabledException e) {
			_display.popup(Message.clientNotificationsAlreadyEnabled());
		}
	}
}
