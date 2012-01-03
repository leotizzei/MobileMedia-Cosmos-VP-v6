/**
 * University of Campinas - Brazil
 * Institute of Computing
 * SED group
 *
 * date: February 2009
 * 
 */
package br.unicamp.ic.sed.mobilemedia.photo.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.photo.spec.dt.Constants;
import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem;


class PhotoController extends PhotoListController{

	private IImageData image;

	private MIDlet midlet;

	private NewLabelScreen screen;

	public PhotoController (MIDlet midlet) {
		super( midlet );
		this.midlet = midlet;
	}

	private void editLabel() {
		//TODO print error message on the screen
		IManager manager = (IManager) ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		String selectedImageName = getSelectedImageName();
		image = filesystem.getImageInfo(selectedImageName);

		NewLabelScreen newLabelScreen = new NewLabelScreen("Edit Label Photo", NewLabelScreen.LABEL_PHOTO);
		newLabelScreen.setCommandListener(this);
		this.setScreen( newLabelScreen );
		setCurrentScreen( newLabelScreen );
		newLabelScreen = null;

	}

	private IImageData getImage() {
		return image;
	}

	private NewLabelScreen getScreen() {
		return screen;
	}

	/* (non-Javadoc)
	 * @see br.unicamp.ic.sed.mobilemedia.photo.impl.IPhotoController#getSelectedImageName()
	 */
	public String getSelectedImageName() {
		
		Displayable display =  Display.getDisplay(midlet).getCurrent();
		if( display instanceof List ){
			List selected = (List)display;
			String name = selected.getString(selected.getSelectedIndex());
			return name;
		}
		else
			return null;
	}

	private boolean goToPreviousScreen() {
		System.out.println("<* PhotoController.goToPreviousScreen() *>");
		String currentScreenName = ScreenSingleton.getInstance().getCurrentScreenName();

		if (currentScreenName.equals(Constants.ALBUMLIST_SCREEN)) {
			System.out.println("Can't go back here...Should never reach this spot");
		} else if (currentScreenName.equals(Constants.IMAGE_SCREEN) || currentScreenName.equals(Constants.NEWLABEL_SCREEN )) {		    
			//Go to the image list here, not the main screen...

			this.showImageList(this.getCurrentStoreName());
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.IMAGELIST_SCREEN);
			return true;
		}
		else if (currentScreenName.equals(Constants.ADDPHOTOTOALBUM_SCREEN)) {

			this.showImageList(this.getCurrentStoreName());
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.IMAGELIST_SCREEN);
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see br.unicamp.ic.sed.mobilemedia.photo.impl.IPhotoController#handleCommand(javax.microedition.lcdui.Command)
	 */
	public boolean handleCommand(Command command)  {
		String label = command.getLabel();
		System.out.println( "<*"+this.getClass().getName()+".handleCommand() *> " + label);

		IManager manager = (IManager) ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		//IPhoto photo = (IPhoto) manager.getRequiredInterface("IPhoto");

		String selectedImageName = getSelectedImageName();
		String storeName = getCurrentStoreName();
		
		if (label.equals("Add")) {
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.ADDPHOTOTOALBUM_SCREEN);
			this.initAddPhotoToAlbum( storeName );
			return true;
			/** Case: Save Add photo * */
		} else if (label.equals("Save Photo")) {
			//TODO print message error on the screen

			AddPhotoToAlbum addPhotoToAlbum = (AddPhotoToAlbum) this.getCurrentScreen();
			String addedPhotoName = addPhotoToAlbum.getPhotoName();
			String addedPhotoPath = addPhotoToAlbum.getPath();
			
			System.out.println("PhotoController -> Save Photo ------------------------------------------------------");
			filesystem.addNewPhotoToAlbum( addedPhotoName , addedPhotoPath , getCurrentStoreName() );
			System.out.println("PhotoController -> Save Photo ------------------------------------------------------");
			
			ScreenSingleton.getInstance().setCurrentScreenName( Constants.ADDPHOTOTOALBUM_SCREEN );
			
			return goToPreviousScreen();
			/** Case: Delete selected Photo from recordstore * */
		} else if (label.equals("Delete")) {
			System.out.println("[PhotoController:handleCommand("+command.getLabel()+") selectedImageName="+selectedImageName);
			System.out.println("[PhotoController:handleCommand("+command.getLabel()+") filesystem="+filesystem);
			filesystem.deleteImage(selectedImageName, getCurrentStoreName());
			System.out.println("[PhotoController:handleCommand] image was deleted!");
			showImageList(getCurrentStoreName());
			System.out.println("[PhotoController:handleCommand] list new images");
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.IMAGELIST_SCREEN);
			return true;

			/** Case: Edit photo label
			 *  [EF] Added in the scenario 02 */
		} else if (label.equals("Edit Label")) {
			this.editLabel();
			return true;

			/** Case: Save new Photo Label */
		} else if (label.equals("Save new label")) {
			NewLabelScreen newLabelScreen = this.getScreen();
			String labelName = newLabelScreen.getLabelName();
			System.out.println("<* PhotoController.handleCommand() *> Save Photo Label = "+ labelName);
			this.getImage().setImageLabel( labelName );
			this.updateImage(image);
			
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.NEWLABEL_SCREEN);
			
			return this.goToPreviousScreen();

			/** Case: Go to the Previous or Fallback screen * */
		} else if (label.equals("Back")) {
			System.out.println("[PhotoController.handleCommand()] Back");
			return this.goToPreviousScreen();

			/** Case: Cancel the current screen and go back one* */
		} else if (label.equals("Cancel")) {
			return this.goToPreviousScreen();

		}

		return false;
	} 

	private void initAddPhotoToAlbum(String albumName) {

		MIDlet midlet = this.getMidlet();

		if( albumName == null){
			System.err.println("Image name is null");
			return;
		}
		else{
			AddPhotoToAlbum addPhotoToAlbum = new AddPhotoToAlbum( albumName );
			addPhotoToAlbum.setCommandListener(this);
			Display.getDisplay( midlet ).setCurrent( addPhotoToAlbum );

		}
	}


	private void setScreen(NewLabelScreen screen) {
		this.screen = screen;
	} 


	private void showImage(String name) {
		// [EF] Instead of replicating this code, I change to use the method "getSelectedImageName()". 		
		Image storedImage = null;
		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");

		storedImage = filesystem.getImageFromRecordStore(getCurrentStoreName(), name);

		//We can pass in the image directly here, or just the name/model pair and have it loaded
		PhotoViewScreen canv = new PhotoViewScreen( storedImage );
		canv.setCommandListener( this );

		setCurrentScreen(canv);
	}

	private void updateImage(IImageData image) {
		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");

		filesystem.updateImageInfo(image, image);

	}

}
