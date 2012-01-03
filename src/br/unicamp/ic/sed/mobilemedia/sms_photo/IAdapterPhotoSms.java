package br.unicamp.ic.sed.mobilemedia.sms_photo;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IPhoto;

public class IAdapterPhotoSms implements IPhoto {

	public void initPhotoViewScreen(Image image) {
		IManager manager = ComponentFactory.createInstance();
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto photo = 
			(br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)
			manager.getRequiredInterface("IPhoto");
		
		photo.initPhotoViewScreen(image);
	}
}