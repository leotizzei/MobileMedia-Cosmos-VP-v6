package br.unicamp.ic.sed.mobilemedia.photo.spec.prov;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;

public interface IPhoto{
	
	public boolean postCommand(Command c);
	
	//add AO v5
	public void initPhotoViewScreen( Image image );
	
}