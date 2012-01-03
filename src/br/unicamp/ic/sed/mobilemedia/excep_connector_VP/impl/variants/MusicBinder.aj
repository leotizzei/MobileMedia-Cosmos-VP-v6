package br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.variants;

import br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.ExceptionAdapter;

import br.unicamp.ic.sed.mobilemedia.music.aspects.XPIIMusicFacade;

public aspect MusicBinder extends ExceptionAdapter {

	public pointcut handleException() : XPIIMusicFacade.playMusic();  
	
}
