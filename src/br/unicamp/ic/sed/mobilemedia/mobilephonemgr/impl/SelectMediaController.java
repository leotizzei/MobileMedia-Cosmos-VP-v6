// [NC] Added in the scenario 07

package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IMobileResources;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IAlbum;

public class SelectMediaController implements CommandListener {	
	
	private IManager manager = ComponentFactory.createInstance();
	private SelectTypeOfMedia screen;
	
	public SelectMediaController( SelectTypeOfMedia mediaScreen ){
		this.screen = mediaScreen;
	}
	
	public void commandAction(Command command, Displayable arg1) {
		
		String label = command.getLabel();
      	System.out.println( "<* SelectMediaController.handleCommand() *>: " + label);
      	if (label.equals("Select")) {
      		this.startAlbum( screen.getString( screen.getSelectedIndex() ) );
      	}else if (label.equals("Exit")) {
			IMobileResources mobileResources = (IMobileResources) manager.getRequiredInterface("IMobileResources");
			mobileResources.destroyApp(true);
      	}

	}
	
	private void startAlbum( String mediaType ){
		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		
		String[] albumNames = filesystem.getAlbumNames();
		
		IAlbum album = (IAlbum) manager.getRequiredInterface("IAlbum");
		album.initAlbumListScreen(albumNames);
	}

}
