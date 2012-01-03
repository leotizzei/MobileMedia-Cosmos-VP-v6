 
package br.unicamp.ic.sed.mobilemedia.sms.impl;

import java.io.IOException;
import java.io.InterruptedIOException;

import javax.microedition.io.Connector;
import javax.microedition.io.PushRegistry;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;
/**
 * @author trevor
 *
 * Insert Comments here
 * 
 */
class SmsMessaging extends BaseMessaging {

	//Port number for SMS message to be sent to:
	//MobilePhoto should have a default port (to send direct to MobilePhoto app if the receiver is listening
	//TODO: What is the default port for standard SMS messaging. So you can send messages to non-MobilePhoto users?
	private String smsSendPort;
	private String smsReceivePort = "1000"; //Use port=1000 as the default for MobilePhoto incoming SMS Messages
	private String destinationPhoneNumber;

	private String smsProtocolPrefix = "sms://";

	private MessageConnection smsConn = null;
	private Message msg;
	private String[] connections; //list of active connections
	
	
	public SmsMessaging() {
		//Set some defaults
		smsSendPort = "1000"; //Change this to whatever the standard SMS listen port is?
		smsReceivePort = "1000";
	}

	public SmsMessaging(String smsDstPort, String destinationPhoneNumber) {
		this.destinationPhoneNumber = destinationPhoneNumber;
		this.smsSendPort = smsDstPort;		
	}

	/* (non-Javadoc)
	 * @see ubc.midp.mobilephoto.core.comms.BaseMessaging#sendImage(byte[])
	 */
	public void sendImage(byte[] imageData) {
		
		String address = destinationPhoneNumber;
		if ( (smsSendPort != null) && (smsSendPort != "") )
			address = smsProtocolPrefix + address + ":" + smsSendPort+1;
		
		System.out.println("SmsMessaging::sendImage: Sending binary message to: " + address);

		MessageConnection smsconn = null;
			 
		//Open the message connection.
		smsconn = (MessageConnection) Connector.open(address);
		
		//Prepare for send of binary data
		BinaryMessage binmsg = (BinaryMessage)smsconn.newMessage( MessageConnection.BINARY_MESSAGE );
		
		//**Device Specific Notes**
		//Motorola only supports sending of a single segment, with a maximum of 132 bytes of data
		
		binmsg.setPayloadData(imageData);

		int i = smsconn.numberOfSegments(binmsg);
		System.out.println("SmsMessaging::sendImage() num segments to send is: " + i);
		
		smsconn.send(binmsg);

		//Close any open connections and perform cleanup
		cleanUpConnections(smsconn);
	}

	/* (non-Javadoc)
	 * @see ubc.midp.mobilephoto.core.comms.BaseMessaging#receiveImage()
	 */
	public byte[] receiveImage() throws InterruptedIOException, IOException {
		
	    System.out.println("SmsMessaging::receiveImage() - start");
	    
	    byte[] receivedData = null;
	    
		String smsConnection = smsProtocolPrefix + ":" + smsReceivePort;
		String senderAddress;
		
		if (smsConn == null) {
			smsConn = (MessageConnection) Connector.open(smsConnection);	
		}

		connections = PushRegistry.listConnections(true);
		if (connections == null || connections.length == 0) {
			System.out.println("Waiting for SMS on " + smsConnection + "...");
		}
		
		// Check for sms connection
		    
	    //TODO: Use MessageListener interface to handle incoming messages,
	    //instead of blocking on the thread
	    
	    //This will block until a message is received
	    System.out.println("DEBUG 1: before smsConn.receive():"+smsConn);
		msg = smsConn.receive();
	    System.out.println("DEBUG 2: after smsConn.receive()");
		
		if (msg != null) {
			senderAddress = msg.getAddress();
			System.out.println("From: " + senderAddress);
			
			//Handle Text Message
			if (msg instanceof TextMessage) {
				String incomingMessage = ((TextMessage) msg).getPayloadText();
				System.out.println("Incoming SMS Message with Payload:" + incomingMessage);
				
			//Handle Binary Message
			} else {
				System.out.println("Incoming Binary SMS Message...");
				StringBuffer buf = new StringBuffer();
				receivedData = ((BinaryMessage) msg).getPayloadData();					
				System.out.println("SmsMessaging::receiveImage: sender address = " + senderAddress.toString());
				System.out.println("SmsMessaging::receiveImage: buffer length = " + buf.length() + " contents = " + buf.toString());
			}
		}

		System.out.println("SmsMessaging::receiveImage() - Finish");
		
		//Return success if we reached this far
		return receivedData;
	}

	/* (non-Javadoc)
	 * @see ubc.midp.mobilephoto.core.comms.BaseMessaging#cleanUpConnections()
	 */
	public void cleanUpConnections(MessageConnection smsConn) {
		
		//Cleanup the connection
		if (smsConn != null) {
			smsConn.close();	
		}
		
	}
	
	public void cleanUpReceiverConnections() {
		
		//Cleanup the connection
		if (smsConn != null) {
			smsConn.close();
			smsConn = null;
		
		}
		
	}
	/**
	 * @return Returns the destinationPhoneNumber.
	 */
	public String getDestinationPhoneNumber() {
		return destinationPhoneNumber;
	}
	/**
	 * @param destinationPhoneNumber The destinationPhoneNumber to set.
	 */
	public void setDestinationPhoneNumber(String destinationPhoneNumber) {
		this.destinationPhoneNumber = destinationPhoneNumber;
	}
	/**
	 * @return Returns the smsReceivePort.
	 */
	public String getSmsReceivePort() {
		return smsReceivePort;
	}
	/**
	 * @param smsReceivePort The smsReceivePort to set.
	 */
	public void setSmsReceivePort(String smsReceivePort) {
		this.smsReceivePort = smsReceivePort;
	}
	/**
	 * @return Returns the smsSendPort.
	 */
	public String getSmsSendPort() {
		return smsSendPort;
	}
	/**
	 * @param smsSendPort The smsSendPort to set.
	 */
	public void setSmsSendPort(String smsSendPort) {
		this.smsSendPort = smsSendPort;
	}
}
