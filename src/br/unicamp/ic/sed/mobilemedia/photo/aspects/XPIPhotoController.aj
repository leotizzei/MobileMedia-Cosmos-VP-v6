package br.unicamp.ic.sed.mobilemedia.photo.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;


public aspect XPIPhotoController {
	
	public pointcut getMediaName( ) : 
		call( public String br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.getSelectedImageName() )
		&& withincode(public boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.handleCommand(Command));
	
	public pointcut getAlbumName( ) : 
		call( protected String br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.getCurrentStoreName() )
		&& withincode( public boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.handleCommand(Command));
	
	
	public pointcut showImage( String imageName ):
		execution(void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoControllerDHandler.showImage(String))
		&& args( imageName );
	
	
	public pointcut editLabel():call(IImageData br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem.getImageInfo(String))
		&& withincode(void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.editLabel());
	
	public pointcut updateImage():
		call(void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.updateImage(IImageData))
		&& withincode(boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.handleCommand(Command));
	
	public pointcut addNewPhotoToAlbum():
		call(void br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem.addNewPhotoToAlbum( String, String, String) )		
		&& withincode(boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.handleCommand(Command));
	
	
	public pointcut callShowImage():
		call(void br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoControllerDHandler.showImage(String));
		
	public pointcut deleteImage():
		call(void br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem.deleteImage(String,String))
		&& withincode(boolean br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoController.handleCommand(Command));
	
}
