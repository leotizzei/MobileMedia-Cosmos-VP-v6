package br.unicamp.ic.sed.mobilemedia.photo_vp_music;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.music.aspects.Photo;

public aspect MusicBinder extends Photo{

	public pointcut handleCommand( Command c , String recordName , String musicName ):
		execution( public boolean MusicStub.postCommand( Command , String , String ))
		&& args( c , recordName , musicName );

	public pointcut addCommand( String command , int type , int posi ):
		execution( public Command MusicStub.addCommand( String , int , int ))
		&& args( command , type , posi );
	
}
