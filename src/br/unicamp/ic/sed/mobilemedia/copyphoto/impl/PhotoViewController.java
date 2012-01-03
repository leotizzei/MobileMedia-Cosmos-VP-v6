/**
 * University of Campinas - Brazil
 * Institute of Computing
 * SED group
 *
 * date: February 2009
 * 
 */
package br.unicamp.ic.sed.mobilemedia.copyphoto.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto;



/**
 * TODO This whole class must be aspectized
 */

class PhotoViewController extends AbstractController {

	private AddPhotoToAlbum addPhotoToAlbum;
	private static final Command backCommand = new Command("Back", Command.BACK, 0);
	private Displayable lastScreen = null;
	
	private void setAddPhotoToAlbum(AddPhotoToAlbum addPhotoToAlbum) {
		this.addPhotoToAlbum = addPhotoToAlbum;
	}

	String imageName = "";

	
	public PhotoViewController(MIDlet midlet,  String imageName) {
		super( midlet );
		this.imageName = imageName;

	}

	private AddPhotoToAlbum getAddPhotoToAlbum() {
		if( this.addPhotoToAlbum == null)
			this.addPhotoToAlbum = new AddPhotoToAlbum("Copy Photo to Album");
		return addPhotoToAlbum;
	}


	/* (non-Javadoc)
	 * @see ubc.midp.mobilephoto.core.ui.controller.ControllerInterface#handleCommand(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public boolean handleCommand(Command c)  {
		String label = c.getLabel();
		System.out.println( "<*"+this.getClass().getName()+".handleCommand() *> " + label);
		
		/** Case: Copy photo to a different album */
		if (label.equals("Copy")) {
			this.initCopyPhotoToAlbum( );
			return true;
		}

		/** Case: Save a copy in a new album */
		else if (label.equals("Save Photo")) {
			return this.savePhoto();
		/*	IManager manager = ComponentFactory.createInstance();
			IPhoto photo = (IPhoto) manager.getRequiredInterface("IPhoto");
			return photo.postCommand( listImagesCommand ); */
			
		}else if( label.equals("Cancel")){
			if( lastScreen != null ){
				MIDlet midlet = this.getMidlet();
				Display.getDisplay( midlet ).setCurrent( lastScreen );
				return true;
			}
		}

		return false;
	}

	
	private void initCopyPhotoToAlbum() {
		
		String title = new String("Copy Photo to Album");
		String labelPhotoPath = new String("Copy to Album:");
		
		AddPhotoToAlbum addPhotoToAlbum = new AddPhotoToAlbum( title );
		addPhotoToAlbum.setPhotoName( imageName );
		addPhotoToAlbum.setLabelPhotoPath( labelPhotoPath );
		
		this.setAddPhotoToAlbum( addPhotoToAlbum );
		
		//Get all required interfaces for this method
		MIDlet midlet = this.getMidlet();
		
		//addPhotoToAlbum.setCommandListener( this );
		lastScreen = Display.getDisplay( midlet ).getCurrent();
		Display.getDisplay( midlet ).setCurrent( addPhotoToAlbum );
				
		addPhotoToAlbum.setCommandListener(this);
	
	}
	
	private boolean savePhoto() {
		System.out.println("[PhotoViewController:savePhoto()]");
		IManager manager = ComponentFactory.createInstance();
		IImageData imageData = null;
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		System.out.println("[PhotoViewController:savePhoto()] filesystem="+filesystem);
	
		imageData = filesystem.getImageInfo(imageName);
		
		AddPhotoToAlbum addPhotoToAlbum = this.getAddPhotoToAlbum();
		
		String photoName = addPhotoToAlbum.getPhotoName(); 
	
		String albumName = addPhotoToAlbum.getPath();
	
		filesystem.addImageData(photoName, imageData, albumName);
		
		if( lastScreen != null ){
			MIDlet midlet = this.getMidlet();
			Display.getDisplay( midlet ).setCurrent( lastScreen );
		}
		
		return true;	
	}	
}
