package br.unicamp.ic.sed.mobilemedia.music_vp_copyphoto.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;

import br.unicamp.ic.sed.mobilemedia.copyphoto.aspects.CopyPhoto_Photo;

public aspect CopyphotoBinder extends CopyPhoto_Photo {

	public pointcut postCommand(Command c):
		execution( public boolean CopyphotoStub.postCommand( Command ) )
		&& args( c );
		
	public pointcut addCopyCommand(Displayable photoViewScreen):
		execution( public void CopyphotoStub.addCopyCommand( Displayable ) )
		&& args( photoViewScreen );
	
	public pointcut showImage(String name):
		execution( public void CopyphotoStub.showImage( String ) )
		&& args( name );
}
