package br.unicamp.ic.sed.mobilemedia.sms.aspects;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sms.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.ISms;

public abstract aspect SmsReceiver {
	
	private byte[] imageSms;
	private boolean bySms = false;
	private IManager manager = ComponentFactory.createInstance();	
	
	public pointcut receiveImage(byte[] imageByte, int a, int b ) : 
		(call(public static Image Image.createImage(byte[],int,int))
		&& args( imageByte,a,b ))
		&& withincode(public boolean br.unicamp.ic.sed.mobilemedia.sms.impl.SmsReceiverController.handleCommand(Command));
	
	after(byte[] imageByte,int a,int b ) : receiveImage( imageByte,a,b ){
		imageSms = imageByte;
		bySms = true;
	}
	
	//==========================================================================
	public abstract pointcut getImageInfo();

	IImageData around() : getImageInfo(){
		if( bySms )
			return null;
		else
			return proceed();	
	}
	//==========================================================================
	
	public abstract pointcut addImageData( String imageName , IImageData imageData, String albumName );
	  
	void around( String imageName , IImageData imageData , String albumName ) : addImageData( imageName , imageData, albumName ){
		System.out.println( "------------Gravando: " + imageName + " : " + albumName + " : " + bySms + " : " + imageData );
		if( bySms ){
			ISms smsFacade = (ISms)manager.getProvidedInterface("ISms");
			smsFacade.addMediaArrayOfBytes(imageName, imageSms, albumName );
			bySms = false;	
		}
		else{
			proceed( imageName , imageData , albumName );
		}
	}	
}
