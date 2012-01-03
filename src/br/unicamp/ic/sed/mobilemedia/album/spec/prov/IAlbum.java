package br.unicamp.ic.sed.mobilemedia.album.spec.prov;

import javax.microedition.lcdui.Command;



public interface IAlbum{

	public void initAlbumListScreen ( String[] albumNames ); 
	
	
	public boolean postCommand(Command command);
	
	
	
	
}