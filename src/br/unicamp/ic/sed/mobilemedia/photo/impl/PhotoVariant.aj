package br.unicamp.ic.sed.mobilemedia.photo.impl;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.photo.spec.dt.Constants;
import br.unicamp.ic.sed.mobilemedia.main.spec.excep.VariabilityException;

public aspect PhotoVariant {

	private PhotoControllerDHandler photoController = new PhotoControllerDHandler();
	
	pointcut handleCommand( Command c , PhotoController controller ) :
		execution( public boolean PhotoController.handleCommand( Command ))
		&& args( c )
		&& target( controller );
	 
	boolean around( Command c , PhotoController controller ) : handleCommand( c , controller ){
		boolean answer = proceed( c , controller );
		try{
			if( answer == false ){
				photoController.setController( controller );
				photoController.setCurrentStoreName( controller.getCurrentStoreName() );
				String imageName = controller.getSelectedImageName();
				if( c.getLabel().equals( "View" )){
					photoController.showImage( imageName );
					ScreenSingleton.getInstance().setCurrentScreenName(Constants.IMAGE_SCREEN);
					answer = true;
				}
			}
		}catch( VariabilityException e){}
		return answer;
	}
}
