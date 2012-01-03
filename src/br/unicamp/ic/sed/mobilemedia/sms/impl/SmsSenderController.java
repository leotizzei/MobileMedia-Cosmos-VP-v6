/*
 * Created on 12-Apr-2005
 *
 */
// #if 

package br.unicamp.ic.sed.mobilemedia.sms.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IAlbum;
import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IFilesystem;

/**
 * @author trevor
 *
 * This class extends the BaseController to provide functionality specific to
 * the SMS (Short Message Service) photo messaging feature. It contains command 
 * handlers for this feature and methods that are only required by this feature. 
 * All non-SMS commands (ie. general ones) are passed on to the parent class (BaseController) 
 * for handling.
 * 
 */
class SmsSenderController extends AbstractController {

	NetworkScreen networkScreen;

	private String selectedImageName = "null";

	/**
	 * @param midlet
	 * @param nextController
	 * @param albumData
	 * @param albumListScreen
	 * @param currentScreenName
	 */
	public SmsSenderController(MIDlet midlet) {
		super( midlet );
	}

	private NetworkScreen getNetworkScreen() {
		return new NetworkScreen("Receiver Details");
	}

	public void setSelectedImageName( String imageName ){
		this.selectedImageName = imageName;
	}
	
	public String getSelectedImageName() {
		return selectedImageName;
	}
	/**
	 * Handle SMS specific events.
	 * If we are given a standard cSystem.out.println("Entrou!!!");ommand that is handled by the BaseController, pass 
	 * the handling off to our super class with the else clause
	 * @throws NullAlbumDataReference 
	 * @throws ImageNotFoundException 
	 * @throws PersistenceMechanismException 
	 */

	public boolean handleCommand(Command c){// throws ImageNotFoundException, NullAlbumDataReference, PersistenceMechanismException {


		String label = c.getLabel();
		System.out.println("SnamemsSenderController::handleCommand: " + label);
		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		/** Case: ... **/
		
		if (label.equals("Send Photo by SMS")) {
			
			System.out.println("Entrou!!!");
			this.networkScreen = this.getNetworkScreen();
			networkScreen.setCommandListener(this);
			this.setCurrentScreen(networkScreen);
			
			return true;

		} else if (label.equals("Send Now")) {

			//Get the data from the currently selected image
			IImageData ii = null;
			//ImageAccessor imageAccessor = null;
			byte[] imageBytes = null;
			
			//String imageName = photo.getSelectedImageName();
			String imageName = this.getSelectedImageName();
			
			System.out.println("ImageName: " + imageName );
			ii = filesystem.getImageInfo( imageName );
			
			//ii = getAlbumData().getImageInfo(selectedImageName);
			String parentAlbum = ii.getParentAlbumName();
			String imageLabel = ii.getImageLabel();
			int recordID = ii.getForeignRecordId();
			
			SmsImageAccessor accessor = new SmsImageAccessor();
			imageBytes = accessor.loadImageBytesFromRMS(parentAlbum, imageLabel, recordID);
//				imageBytes = filesystem.loadImageBytesFromRMS( parentAlbum , imageLabel, recordID);
			
			//imageBytes = imageAccessor.loadImageBytesFromRMS(ii.getParentAlbumName(), ii.getImageLabel(), ii.getForeignRecordId());
	
			System.out.println("SmsController::handleCommand - Sending bytes for image " + ii.getImageLabel() + " with length: " + imageBytes.length);

			//Get the destination info - set some defaults just in case
			String smsPort = "1000";
			String destinationAddress = "5550001";
			String messageText = "Binary Message (No Text)";

			smsPort = networkScreen.getRecPort();
			destinationAddress = networkScreen.getRecPhoneNum();
			System.out.println("SmsController:handleCommand() - Info from Network Screen is: " + smsPort + " and " + destinationAddress);
			
			SmsSenderThread smsS = new SmsSenderThread(smsPort,destinationAddress,messageText);
			smsS.setBinaryData(imageBytes);
			new Thread(smsS).start();
			return true;

			
		} else if (label.equals("Cancel")) {

			//TODO: If they want to cancel sending the SMS message, send them back to main screen
			IAlbum album = (IAlbum) manager.getRequiredInterface("IAlbum");
			album.initAlbumListScreen( filesystem.getAlbumNames() );
			return true;

		} 

		return false;
	}
}
