package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IAlbum;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IMobileResources;

public class MediasBaseController extends BaseController{

	private IManager manager = ComponentFactory.createInstance();
	
	public MediasBaseController( MIDlet midlet ){
		super( midlet );
	}
	
	public void init() {
		SelectTypeOfMedia screen = new SelectTypeOfMedia();
		SelectMediaController controller = new SelectMediaController( screen );
		
		
		screen.setCommandListener( controller );
		IMobileResources mobile = (IMobileResources)manager.getRequiredInterface("IMobileResources");
		
		screen.initMenu();
		
		Display.getDisplay( mobile.getMainMIDlet() ).setCurrent( screen );
	}

	public boolean handleCommand(Command command) {

		String label = command.getLabel();
		System.out.println( "<*"+this.getClass().getName()+".handleCommand() *> " + label);

		/** Case: Exit Application **/
		if (label.equals("Exit")) {
			
			IManager manager = ComponentFactory.createInstance();
			IMobileResources mobileResources = (IMobileResources) manager.getRequiredInterface("IMobileResources");
			
			if( Display.getDisplay( mobileResources.getMainMIDlet() ).getCurrent() instanceof SelectTypeOfMedia )
				mobileResources.destroyApp(true);
			else
				init();
			return true;

			/** Case: Go to the Previous or Fallback screen * */
		} else if (label.equals("Back")) {
			goToAlbumScreen();
			return true;

			/** Case: Cancel the current screen and go back one* */
		} else if (label.equals("Cancel")) {
			goToAlbumScreen();
			return true;

		}
		return false;

	}

	private void goToAlbumScreen() {
		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		
		String[] albumNames = filesystem.getAlbumNames();
		
		IAlbum album = (IAlbum) manager.getRequiredInterface("IAlbum");
		album.initAlbumListScreen(albumNames);
	}
	
}
