package br.unicamp.ic.sed.mobilemedia.copyphoto.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov.ICopyPhoto;
import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.req.IMobileResources;

public class ICopyPhotoFacade implements ICopyPhoto {

	private PhotoViewController photoViewCtr;

	private IManager manager;

	public ICopyPhotoFacade(IManager mgr) {
		this.manager = mgr;

	}

	private PhotoViewController createPhotoViewCtr(String photoName) {
		
		IMobileResources mobResources = (IMobileResources) manager.getRequiredInterface("IMobileResources");
		MIDlet midlet = mobResources.getMainMIDlet();
		photoViewCtr = new PhotoViewController( midlet , photoName );
		
		return photoViewCtr;
	}



	public boolean postCommand(Command command,String photoName) {
		PhotoViewController photoViewController = this.createPhotoViewCtr(photoName);
		return photoViewController.postCommand(command);
	}

	/**
	 * Get the last selected image from the Photo List screen.
	 * TODO: This really only gets the selected List item. 
	 * So it is only an image name if you are on the PhotoList screen...
	 * Need to fix this
	 */
	private String getSelectedImageName(MIDlet midlet) {
		Displayable d = Display.getDisplay(midlet).getCurrent();
		System.out.println("[ICopyPhotoFacade] d="+d);
		
		List selected = (List) Display.getDisplay(midlet).getCurrent();
		if (selected == null)
			System.out.println("Current List from display is NULL!");
		String name = selected.getString(selected.getSelectedIndex());
		return name;
	}




}
