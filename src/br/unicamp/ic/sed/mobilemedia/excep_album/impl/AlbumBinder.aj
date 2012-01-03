package br.unicamp.ic.sed.mobilemedia.excep_album.impl;

import br.unicamp.ic.sed.mobilemedia.album.aspects.XPIAlbumController;


public aspect AlbumBinder extends ExceptionAdapter {

	public pointcut handleException() : XPIAlbumController.handleCommand();
	
}
