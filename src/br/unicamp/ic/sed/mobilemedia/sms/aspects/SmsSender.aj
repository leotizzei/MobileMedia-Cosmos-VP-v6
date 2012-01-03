package br.unicamp.ic.sed.mobilemedia.sms.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.sms.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.ISms;

public abstract aspect SmsSender {

	private Command sendSms = new Command( "Send Photo by SMS" , Command.ITEM , 1 );
	private IManager manager = ComponentFactory.createInstance();
	
	//=================================================================================
	public abstract  pointcut constructingPhotoViewScreen(javax.microedition.lcdui.Canvas photoViewScreen);
	
	after(javax.microedition.lcdui.Canvas photoViewScreen):
		constructingPhotoViewScreen(photoViewScreen){
		
		photoViewScreen.addCommand( sendSms );
	}
	
	//===================================================================================
	
	abstract public pointcut postCommand(Command c);

	boolean around(Command c) : postCommand(c){
		ISms sms = (ISms)manager.getProvidedInterface("ISms");
		if( sms.postCommand(c) )
			return true;
		else
			return proceed(c);
	}
	
	//===================================================================================
	public abstract pointcut showImage(String name);
	
	before( String name ):showImage(name){
		ISms sms = (ISms)manager.getProvidedInterface("ISms");
		sms.setSelectedImageName( name );
	}
	
}
