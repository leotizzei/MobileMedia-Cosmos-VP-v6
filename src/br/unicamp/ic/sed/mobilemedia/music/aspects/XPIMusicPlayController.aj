package br.unicamp.ic.sed.mobilemedia.music.aspects;

import javax.microedition.lcdui.Command;

public aspect XPIMusicPlayController {
	public pointcut handleCommand( Command c ) : 
		execution( public boolean br.unicamp.ic.sed.mobilemedia.music.impl.MusicPlayController.handleCommand(Command))
		&& args( c );
}
