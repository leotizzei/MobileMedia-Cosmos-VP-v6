package br.unicamp.ic.sed.mobilemedia.photo.aspects;

import javax.microedition.lcdui.Command;

public aspect XPIPhoto {
	
	public pointcut postCommand(Command c):
		execution(public boolean br.unicamp.ic.sed.mobilemedia.photo..*.postCommand(Command))
		&& args(c);

}
