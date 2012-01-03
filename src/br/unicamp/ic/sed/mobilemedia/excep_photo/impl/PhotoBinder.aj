package br.unicamp.ic.sed.mobilemedia.excep_photo.impl;

import br.unicamp.ic.sed.mobilemedia.excep_album.impl.ExceptionAdapter;

import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoListController;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoController;

public aspect PhotoBinder extends ExceptionAdapter{
	
	public pointcut handleException():
		XPIPhotoController.deleteImage() 
		|| XPIPhotoController.updateImage() 
		|| XPIPhotoController.callShowImage() 
		|| XPIPhotoController.addNewPhotoToAlbum()
		|| XPIPhotoController.editLabel()
		
		|| XPIPhotoListController.showImageListException()
		|| XPIPhotoListController.handleCommandException();
	
}
