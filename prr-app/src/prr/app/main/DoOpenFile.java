package prr.app.main;

import prr.NetworkManager;
import prr.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;
import java.io.FileNotFoundException;

import prr.exceptions.UnavailableFileException;
//Add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

	DoOpenFile(NetworkManager receiver) {
		super(Label.OPEN_FILE, receiver);
                //FIXME add command fields
                addStringField("filename", Prompt.openFile());
	}

	@Override
	protected final void execute() throws CommandException {
                try {
                        _receiver.load(stringField("filename"));
                } catch (UnavailableFileException e) {
                        _display.popup(Message.fileNotFound());
                } catch (IOException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
	}
}
