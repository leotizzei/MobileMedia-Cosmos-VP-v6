package br.unicamp.ic.sed.mobilemedia.photo.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect XPIPhotoListController {

	public pointcut getImages(String recordName):
		(call(IImageData[] br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem.getImages(String))
				&& args(recordName))
				&& withincode(void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListController.showImageList(String  ) );

	public pointcut setCurrentStoreName(String storeName):
		call(void br.unicamp.ic.sed.mobilemedia.photo.impl.ScreenSingleton.setCurrentStoreName( String ))
		&& args( storeName)
		&& withincode(public boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListController.handleCommand(Command) );
	
	public pointcut handleCommandException() : 
		(execution(public boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListController.handleCommand(Command)));
		
	public pointcut showImageListException() : (execution(protected void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoListController.showImageList(String )));
	 
}
