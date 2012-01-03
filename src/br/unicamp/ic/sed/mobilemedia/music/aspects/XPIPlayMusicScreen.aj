package br.unicamp.ic.sed.mobilemedia.music.aspects;

import javax.microedition.lcdui.Form;

public aspect XPIPlayMusicScreen {
	
	public pointcut setButtons( Form display ) : 
		call( private void br.unicamp.ic.sed.mobilemedia.music.impl.PlayMediaScreen.setButtons( Form ))
		&& args( display );
	
}
