package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import prr.app.exceptions.UnknownTerminalKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

import prr.exceptions.*;

//FIXME add more imports if needed

/**
 * Command for sending a text communication.
 */
class DoSendTextCommunication extends TerminalCommand {

        DoSendTextCommunication(Network context, Terminal terminal) {
                super(Label.SEND_TEXT_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
                addStringField("destinationID", Prompt.terminalKey());
                addStringField("textMessage", Prompt.textMessage());
        }

        @Override
        protected final void execute() throws CommandException {
                //FIXME implement command
                try {
                        _receiver.startTextCommunication(stringField("destinationID"), stringField("textMessage"), _network);
                } catch (prr.exceptions.DestinationOffException e) {
                        _display.popup(Message.destinationIsOff(stringField("destinationID")));
                } catch (prr.exceptions.UnknownTerminalKeyException e) {
                        throw new UnknownTerminalKeyException(stringField("destinationID"));
                }
        }
}
