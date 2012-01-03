
package br.unicamp.ic.sed.mobilemedia.photo.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.photo.spec.dt.Constants;
import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto;
import br.unicamp.ic.sed.mobilemedia.photo.spec.req.IMobileResources;

class IPhotoFacade implements IPhoto{

	private MIDlet midlet;

	private PhotoController photoController;

	private PhotoListController photoListController;


	
	private MIDlet getMidlet() {
		if( this.midlet == null){
			IManager manager = ComponentFactory.createInstance();
			IMobileResources mobileResources = (IMobileResources) manager.getRequiredInterface("IMobileResources");
			this.midlet = mobileResources.getMainMIDlet();
		}
		return midlet;
	}


	private PhotoController getPhotoController() {
		if( this.photoController == null){
			MIDlet midlet = this.getMidlet();
			this.photoController = new PhotoController( midlet );
		}

		return this.photoController;
	}


	private PhotoListController getPhotoListController() {
		if( this.photoListController == null){
			MIDlet midlet = this.getMidlet();
			this.photoListController = new PhotoListController( midlet );
		}
		return photoListController;
	}


	

	public boolean postCommand(Command c) {
		System.out.println(this.getClass().getName()+".postCommand("+c.getLabel()+")");
		PhotoController photoController = this.getPhotoController();
		System.out.println("photoController="+photoController);
		PhotoListController photoListController = this.getPhotoListController();

		System.out.println("photoListController="+photoListController);
		
		photoController.setNextController(photoListController);
	
		return photoController.postCommand(c);

	}
	
	public void initPhotoViewScreen( Image image ){
		PhotoController photoController = this.getPhotoController();
		PhotoListController photoListController = this.getPhotoListController();		
		photoController.setNextController(photoListController);
	
		PhotoViewScreen photoScreen = new PhotoViewScreen( image );
		photoScreen.setCommandListener( photoController );

		ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGE_SCREEN );
		Display.getDisplay( midlet ).setCurrent( photoScreen );
	}


	
	public String getSelectedPhoto() {
		PhotoController photoCtr = this.getPhotoController();
		return photoCtr.getSelectedImageName();
	}

}