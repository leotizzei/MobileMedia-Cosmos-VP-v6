package br.unicamp.ic.sed.mobilemedia.music.aspects;

import br.unicamp.ic.sed.mobilemedia.music.impl.MusicController;

public aspect XPIIMusicFacade {
	
	public pointcut play( String albumName, String musicName ) :
		call( public void MusicController.playMusic(String,String))
		&& args( albumName , musicName );
}
