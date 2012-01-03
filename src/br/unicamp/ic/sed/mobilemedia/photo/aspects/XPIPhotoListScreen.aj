package br.unicamp.ic.sed.mobilemedia.photo.aspects;

import javax.microedition.lcdui.Command;

public aspect XPIPhotoListScreen {

	//public static final Command sortCommand = new Command("Sort by Views", Command.ITEM, 1);

	public pointcut initMenu(br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListScreen photoListScreen):
		call(void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListScreen.initMenu())
		&& target( photoListScreen ); 

	public pointcut addCommand( String command , int commandType , int posi ):
		call( public Command.new(String,int,int))
		&& args( command , commandType , posi )
		&& within( br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListScreen );
	
	
}
