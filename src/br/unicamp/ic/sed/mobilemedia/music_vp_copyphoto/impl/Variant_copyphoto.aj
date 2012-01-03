package br.unicamp.ic.sed.mobilemedia.music_vp_copyphoto.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import br.unicamp.ic.sed.mobilemedia.music.aspects.XPIIMusicFacade;
import br.unicamp.ic.sed.mobilemedia.music.aspects.XPIMusicPlayController;
import br.unicamp.ic.sed.mobilemedia.music.aspects.XPIPlayMusicScreen;

public privileged aspect Variant_copyphoto {

	private CopyphotoStub copyphoto = new CopyphotoStub(); 
	
	public pointcut postCommand(Command c):XPIMusicPlayController.handleCommand(c);
		
	boolean around( Command c ) : postCommand( c ){
		if( !copyphoto.postCommand( c ))
			return proceed( c );
		return true;
	}
	
	public pointcut setButtons(Form photoViewScreen):
		XPIPlayMusicScreen.setButtons(photoViewScreen);
	
	after( Form photoViewScreen ) :  setButtons(photoViewScreen){
		copyphoto.addCopyCommand( photoViewScreen );
	}
	
	public pointcut playMusic(String albumName , String musicName):
		XPIIMusicFacade.play(albumName,musicName);
	
	after( String albumName , String musicName ) : playMusic(albumName, musicName){
		copyphoto.showImage( musicName );
	}
}
