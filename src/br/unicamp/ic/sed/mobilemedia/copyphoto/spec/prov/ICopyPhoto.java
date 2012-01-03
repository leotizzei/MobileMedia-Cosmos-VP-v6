package br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov;

import javax.microedition.lcdui.Command;

public interface ICopyPhoto {
	
	public boolean postCommand(Command command, String photoName);

}
