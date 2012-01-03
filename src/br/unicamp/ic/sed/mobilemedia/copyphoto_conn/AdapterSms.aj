package br.unicamp.ic.sed.mobilemedia.copyphoto_conn;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sms.aspects.SmsReceiver;
import br.unicamp.ic.sed.mobilemedia.copyphoto.aspects.XPIPhotoViewController;

public aspect AdapterSms extends SmsReceiver{
	
	public pointcut getImageInfo() : XPIPhotoViewController.getImageInfo();

	public pointcut addImageData( String imageName , IImageData imageData, String albumName ):
		XPIPhotoViewController.addImageData( imageName , imageData , albumName );
}
