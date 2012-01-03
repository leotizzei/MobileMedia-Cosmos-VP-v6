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
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.photo.spec.dt.Constants;
import br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.photo.spec.req.IFilesystem;

class PhotoListController extends AbstractController {

	private MIDlet midlet;

	private PhotoController photoController;

	private PhotoListScreen photoListScreen;

	public PhotoListController(MIDlet midlet) {
		super( midlet );
		this.midlet = midlet;

	}

	public boolean handleCommand(Command command)  {
		String label = command.getLabel();
		System.out.println( "<*"+this.getClass().getName()+".handleCommand() *> " + label);

		/** Case: Select PhotoAlbum to view **/
		if (label.equals("Select")) {
			// Get the name of the PhotoAlbum selected, and load image list from
			// RecordStore


			List down = (List) Display.getDisplay(midlet).getCurrent();

			/*String currentScreen = ScreenSingleton.getInstance().getCurrentScreenName();
			System.out.println("====================== currentScreen = "+currentScreen);*/
			//if( ( currentScreen == null) || currentScreen.equals("AlbumListScreen")){
			String selectedIndex = down.getString( down.getSelectedIndex() );
			//System.out.println("[PhotoListController.handleCommand] selectedIndex="+selectedIndex);
			ScreenSingleton.getInstance().setCurrentStoreName( selectedIndex );
			//}

			String storeName = getCurrentStoreName();
			//System.out.println("[PhotoListController.handleCommand("+command.getLabel()+")] storeName="+storeName);
			this.showImageList( storeName );
			ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGELIST_SCREEN);
			return true;
		}

		return false;
	}



	/**
	 * Show the list of images in the record store
	 * TODO: Refactor - Move this to ImageList class
	 * @throws UnavailablePhotoAlbumException 
	 */
	protected void showImageList(String recordName ){
		
		if (recordName == null)
			recordName = getCurrentStoreName();

		

		IManager manager = ComponentFactory.createInstance();

		PhotoController photoController = this.getPhotoController();

		photoController.setNextController(this);
		

		PhotoListScreen photoListScreen = this.getPhotoListScreen();
		

		photoListScreen.setCommandListener(photoController);
		

		//Command selectCommand = new Command("Open", Command.ITEM, 1);
		photoListScreen.initMenu();


		IImageData[] images = null;

		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");

		images = filesystem.getImages(recordName);



		if (images==null) 
			return;

		//loop through array and add labels to list
		for (int i = 0; i < images.length; i++) {
			System.out.println("[PhotoListController.showImageList(..)]  images.length ="+images.length);
			if (images[i] != null) {
				//Add album name to menu list

				String imageLabel = images[i].getImageLabel();
				System.out.println("[PhotoListController.showImageList(..)] imageLabel = "+imageLabel);
	
				photoListScreen.append(imageLabel, null);

			}
		}

		this.setCurrentScreen(photoListScreen);

	}

	private PhotoController getPhotoController() {
		if( this.photoController == null){
			MIDlet midlet = this.getMidlet();
			this.photoController = new PhotoController( midlet );
		}
		return photoController;
	}

	private PhotoListScreen getPhotoListScreen() {

		this.photoListScreen = new PhotoListScreen();

		return photoListScreen;
	}



}
