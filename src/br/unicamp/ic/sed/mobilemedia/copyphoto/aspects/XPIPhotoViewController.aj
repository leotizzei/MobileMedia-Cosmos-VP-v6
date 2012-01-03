package br.unicamp.ic.sed.mobilemedia.copyphoto.aspects;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect XPIPhotoViewController {

	public pointcut getImageInfo():
		call( public IImageData br.unicamp.ic.sed.mobilemedia.copyphoto.spec.req.IFilesystem.getImageInfo(String))
		&& withincode( private boolean br.unicamp.ic.sed.mobilemedia.copyphoto.impl.PhotoViewController.savePhoto() );
	
	public pointcut addImageData( String imageName , IImageData imageData, String albumName ):
		(call( public void br.unicamp.ic.sed.mobilemedia.copyphoto.spec.req.IFilesystem.addImageData(String,IImageData,String))
		&& args( imageName , imageData , albumName ))
		&& withincode( private boolean br.unicamp.ic.sed.mobilemedia.copyphoto.impl.PhotoViewController.savePhoto() );
		
}
