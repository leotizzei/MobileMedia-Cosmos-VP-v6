package br.unicamp.ic.sed.mobilemedia.sms.aspects;

import javax.microedition.io.Connection;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.Message;
import java.io.IOException;

public aspect XPISmsMessaging {
	pointcut openConnection() : call( public static Connection javax.microedition.io.Connector.open(String) )
										&& withincode( public void br.unicamp.ic.sed.mobilemedia.sms.impl.SmsMessaging.sendImage( byte[] ) );
	
	pointcut sendMessage() : call( public void MessageConnection.send( Message) )
									&& withincode( public void br.unicamp.ic.sed.mobilemedia.sms.impl.SmsMessaging.sendImage( byte[] ) );
	
	public pointcut exceptionsInSendImage() : openConnection() || sendMessage();
	
	declare soft : Exception : exceptionsInSendImage() ;
	
	//***********************************************************************************************
	
	public pointcut exceptionsInReceiveImage() : call( public static Connection javax.microedition.io.Connector.open(String) )
	&& withincode( public byte[] br.unicamp.ic.sed.mobilemedia.sms.impl.SmsMessaging.receiveImage( ) );
	
	declare soft : IOException : exceptionsInReceiveImage();  

	//***********************************************************************************************
	
	public pointcut closeConnections() : call( public void javax.microedition.io.Connection.close() )
											&& withincode( public void br.unicamp.ic.sed.mobilemedia.sms.impl.SmsMessaging.cleanUpConnections(MessageConnection) )
											|| withincode( public void br.unicamp.ic.sed.mobilemedia.sms.impl.SmsMessaging.cleanUpReceiverConnections() );

	declare soft : IOException : closeConnections();
	
}
