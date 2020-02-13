package tutorial691online.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class SampleHandler extends AbstractHandler {

	private static final String CONSOLE_NAME = "691 exception antipattern analysis";
	private static MessageConsole myConsole;
	private static MessageConsoleStream out;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		SampleHandler.myConsole = findConsole(CONSOLE_NAME);
		SampleHandler.out = myConsole.newMessageStream();
		
		DetectException detectException = new DetectException();
		detectException.execute(event);
		
		return null;
	}
	
	private MessageConsole findConsole(String name) {
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();
		
		for (int i = 0; i < existing.length; i++)
		   if (name.equals(existing[i].getName()))
		      return (MessageConsole) existing[i];
		
		//no console found, so create a new one
		MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[]{myConsole});
		return myConsole;
	}
	
	// To print messages into the Debug view, not just in the console here.
	static public void printMessage(String message) {
		out.println(message);
	}
}
