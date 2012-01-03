 

package br.unicamp.ic.sed.mobilemedia.sms.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.ISms;
import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IMobileResources;

public class ISmsFacade implements ISms{

	private SmsSenderController senderController = null;
	private ControllerInterface nextController;
	private SmsImageAccessor accessor = new SmsImageAccessor();
	
	public ISmsFacade(){
		IManager manager = ComponentFactory.createInstance();
		IMobileResources mobile = (IMobileResources) manager.getRequiredInterface("IMobileResources");
		MIDlet midlet = mobile.getMainMIDlet();
		
		senderController = new SmsSenderController( midlet );
		senderController.setNextController(nextController);
		
		SmsReceiverController controller = new SmsReceiverController( midlet );
		//controller.setNextController(albumController);
		SmsReceiverThread smsR = new SmsReceiverThread(midlet , controller);
		System.out.println("SmsController::Starting SMSReceiver Thread");
		new Thread(smsR).start();
	}
	
	public boolean postCommand(Command command) {
		if( senderController.postCommand(command))
			return true;
		else if( nextController != null )
			return nextController.postCommand(command);
		return false;
	}

	public ControllerInterface getNextController() {
		return nextController;
	}
	
	public void setNextController(ControllerInterface controller) {
		nextController = controller;		
	}
	
	public void addMediaArrayOfBytes(String photoname, byte[] data1, String albumname){
		accessor.addMediaArrayOfBytes(photoname, data1, albumname);
	}
	
	public byte[] loadImageBytesFromRMS(String recordName, String imageName,int recordId){
		return accessor.loadImageBytesFromRMS(recordName, imageName, recordId);
	}

	public void setSelectedImageName(String imageName) {
		this.senderController.setSelectedImageName(imageName);		
	}
	
	
}
