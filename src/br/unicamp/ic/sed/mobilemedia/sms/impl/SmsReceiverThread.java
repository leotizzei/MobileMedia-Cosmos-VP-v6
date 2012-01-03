// #if 
package br.unicamp.ic.sed.mobilemedia.sms.impl;

import java.io.IOException;
import java.io.InterruptedIOException;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.midlet.MIDlet;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;


/**
 * 
 */
class SmsReceiverThread implements Runnable { //extends BaseThread {
	
	SmsReceiverController smsReceiverCtr = null;

		
	String[] connections; 		//Connections detected at start up.
	String smsPort; 			//The port on which we listen for SMS messages
	MessageConnection smsconn; 	//SMS message connection for inbound text messages.
	Message msg; 				//Current message read from the network.
	String senderAddress; 		//Address of the message's sender
	
    Command acceptPhotoCommand = new Command("Accept Photo", Command.ITEM, 1);
    Command rejectPhotoCommand = new Command("Reject Photo", Command.ITEM, 1);
    
    Command errorNotice = new Command("Ok", Command.ITEM, 1);

	/**
	 * Initialize the MIDlet with the current display object and graphical
	 * components.
	 */
	public SmsReceiverThread(MIDlet midlet, SmsReceiverController controller) {
		this.smsReceiverCtr = controller;
		//smsPort = getAppProperty("SMS-Port");
		smsPort = "1000"; //getAppProperty("SMS-Port");
	}

	/**
	 * Initialize the MIDlet with the current display object and graphical
	 * components.
	 * 
	 * Pass in the controller so we can notify it when a photo/message is received via SMS
	 */
	
	/** Message reading thread. */
	public void run() {
		SmsMessaging smsMessenger = new SmsMessaging();

		while(true){
			System.out.println("Starting SMSReceiver::run()");
			
			smsMessenger.setSmsReceivePort(smsPort);
			byte[] receivedData = null;
			try {
				receivedData = smsMessenger.receiveImage();
			} catch (InterruptedIOException e) {
				Alert alert = new Alert("Error Incoming Photo");
				alert.setString("" + "You have just received a bad fragmentated photo which was not possible to recovery.");
				alert.addCommand(errorNotice);
				alert.setCommandListener( smsReceiverCtr );
				smsReceiverCtr.setCurrentScreen( alert );
				smsMessenger.cleanUpReceiverConnections();
				continue;

			} catch (IOException e) {
				Alert alert = new Alert("Error Incoming Photo");
				alert.setString("" + "You have just received a bad fragmentated photo which was not possible to recovery.");
				alert.addCommand(errorNotice);
				alert.setCommandListener( smsReceiverCtr );
				smsReceiverCtr.setCurrentScreen( alert );
				smsMessenger.cleanUpReceiverConnections();
				continue;
			}	
			System.out.println("BEFORE ALERT CODE");
			Alert alert = new Alert("New Incoming Photo");
			alert.setString("A MobilePhoto user has sent you a Photo. Do you want to accept it?");
			alert.addCommand(acceptPhotoCommand);
			alert.addCommand(rejectPhotoCommand);
			smsReceiverCtr.setIncommingData(receivedData);
			alert.setCommandListener(smsReceiverCtr);

			smsReceiverCtr.setCurrentScreen(alert);
			
		}
	}
}
