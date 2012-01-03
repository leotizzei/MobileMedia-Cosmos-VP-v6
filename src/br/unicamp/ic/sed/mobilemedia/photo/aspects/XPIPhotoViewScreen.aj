package br.unicamp.ic.sed.mobilemedia.photo.aspects;

import javax.microedition.lcdui.Image;

public aspect XPIPhotoViewScreen {
	
	public pointcut constructingPhotoViewScreen(br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoViewScreen photoViewScreen):
		execution(public br.unicamp.ic.sed.mobilemedia.photo.impl.PhotoViewScreen.new(Image))
		&&target(photoViewScreen);
	
}
