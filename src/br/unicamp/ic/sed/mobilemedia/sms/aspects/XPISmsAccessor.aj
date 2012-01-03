package br.unicamp.ic.sed.mobilemedia.sms.aspects;

import javax.microedition.rms.RecordStoreException;
import br.unicamp.ic.sed.mobilemedia.sms.spec.excep.InvalidImageDataException;

public aspect XPISmsAccessor {
	public pointcut loadImage() : 
		execution( protected byte[] br.unicamp.ic.sed.mobilemedia.sms.impl.SmsImageAccessor.loadImageBytesFromRMS(String,String,int));

	declare soft : RecordStoreException : loadImage();
	
	public pointcut addImage() :
		execution( public void  br.unicamp.ic.sed.mobilemedia.sms.impl.SmsImageAccessor.addMediaArrayOfBytes(String,byte[],String));

	declare soft : RecordStoreException : addImage();
	declare soft : InvalidImageDataException : addImage(); 
}
