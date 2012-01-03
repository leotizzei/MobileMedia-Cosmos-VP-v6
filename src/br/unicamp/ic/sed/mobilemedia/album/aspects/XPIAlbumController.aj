package br.unicamp.ic.sed.mobilemedia.album.aspects;


import javax.microedition.lcdui.Command;
import br.unicamp.ic.sed.mobilemedia.album.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.album.spec.excep.PersistenceMechanismException;


public aspect XPIAlbumController {
  
	public pointcut handleCommand( ) : 
		execution(public boolean br.unicamp.ic.sed.mobilemedia.album.impl.AlbumController.handleCommand(Command));

	declare soft:InvalidPhotoAlbumNameException: handleCommand();	
	declare soft:PersistenceMechanismException: handleCommand();

	
}
