package br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.variants;

import br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.ExceptionAdapter;

import br.unicamp.ic.sed.mobilemedia.sms.aspects.XPISenderController;
import br.unicamp.ic.sed.mobilemedia.sms.aspects.XPISmsAccessor;
import br.unicamp.ic.sed.mobilemedia.sms.aspects.XPISmsMessaging;

public aspect SMSBinder extends ExceptionAdapter{

	public pointcut handleException() : XPISenderController.getImageInfoToSend()
					
									||  XPISmsAccessor.loadImage()
									||  XPISmsAccessor.addImage()
							
									|| XPISmsMessaging.exceptionsInSendImage()
									|| XPISmsMessaging.exceptionsInReceiveImage()
									|| XPISmsMessaging.closeConnections();
	
}
