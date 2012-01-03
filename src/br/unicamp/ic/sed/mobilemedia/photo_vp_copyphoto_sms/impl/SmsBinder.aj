package br.unicamp.ic.sed.mobilemedia.photo_vp_copyphoto_sms.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Canvas;

import br.unicamp.ic.sed.mobilemedia.sms.aspects.SmsSender;

public aspect SmsBinder extends SmsSender {

	public pointcut postCommand(Command c):
		execution( public boolean SmsStub.postCommand( Command ) )
		&& args( c );
		
	public pointcut constructingPhotoViewScreen(Canvas photoViewScreen):
		execution( public void SmsStub.constructingPhotoViewScreen( Canvas) )
		&& args( photoViewScreen );
	
	public pointcut showImage(String name):
		execution( public void SmsStub.showImage( String ) )
		&& args( name );
}
