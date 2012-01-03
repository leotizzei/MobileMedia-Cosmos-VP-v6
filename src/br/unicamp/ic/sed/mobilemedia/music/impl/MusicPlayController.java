// [NC] Added in the scenario 07
package br.unicamp.ic.sed.mobilemedia.music.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

public class MusicPlayController extends AbstractController{
	// [NC] Added in the scenario 07
	private String mediaName;
	
	private PlayMediaScreen pmscreen;
	private Displayable lastDisplay;
	
	public MusicPlayController(MIDlet midlet, PlayMediaScreen pmscreen, String mediaName ) {
		super(midlet);
		this.pmscreen = pmscreen;
		this.mediaName = mediaName;
	}

	public boolean handleCommand(Command command) {
		String label = command.getLabel();
		System.out.println( "<* MusicPlayController.handleCommand() *> " + label);

		/** Case: Copy photo to a different album */
		if (label.equals("Start")) {
			pmscreen.startPlay();
			return true;
		}else if (label.equals("Stop")) {
			pmscreen.pausePlay();
			return true;
		}else if ((label.equals("Back"))||(label.equals("Cancel"))){
			pmscreen.pausePlay();
			pmscreen.dispose();
			return true;
		}
		return false;
	}	
}