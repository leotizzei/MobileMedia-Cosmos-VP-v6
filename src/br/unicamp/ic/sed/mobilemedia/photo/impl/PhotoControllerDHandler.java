package br.unicamp.ic.sed.mobilemedia.photo.impl;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem;

class PhotoControllerDHandler implements IPhotoControllerDHandler {

	private String storeName = null;
	private AbstractController controller;
	
	public void setCurrentStoreName( String currentStoreName ){
		storeName = currentStoreName;
	}
	
	public void setController( AbstractController controller ){
		this.controller = controller;
	}
	
	
	public void showImage(String name )  {
		// [EF] Instead of replicating this code, I change to use the method "getSelectedImageName()". 		
		Image storedImage = null;
		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
 
		storedImage = filesystem.getImageFromRecordStore( storeName , name);

		//We can pass in the image directly here, or just the name/model pair and have it loaded
		PhotoViewScreen canv = new PhotoViewScreen( storedImage );
		canv.setCommandListener( controller );

		controller.setCurrentScreen( canv );
	}
	
}
