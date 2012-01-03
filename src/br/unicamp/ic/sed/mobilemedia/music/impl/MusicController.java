package br.unicamp.ic.sed.mobilemedia.music.impl;

import java.io.InputStream;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.music.spec.dt.Constants;
import br.unicamp.ic.sed.mobilemedia.music.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.music.spec.req.IFilesystemMusic;
import br.unicamp.ic.sed.mobilemedia.music.spec.req.IMobileResources;

public class MusicController {
	private MIDlet midlet;
	private MusicPlayController musicPlayController = null;
	private PlayMediaScreen playMediaScreen = null;
	
	private String musicName;
	private IManager manager = ComponentFactory.createInstance();
	
	public MusicController(){
		IManager manager = ComponentFactory.createInstance();
		IMobileResources mobileResources = (IMobileResources) manager.getRequiredInterface("IMobileResources");
		this.midlet = mobileResources.getMainMIDlet();
	}

	public String getImageName() {
		return this.musicName;
	}

	public void playMusic(String albumName, String musicName) {
		this.musicName = musicName;

		IFilesystemMusic filesystem = (IFilesystemMusic) manager.getRequiredInterface("IFilesystemMusic");	
		InputStream music = filesystem.getMusicFromRecordStore(albumName, musicName);
				
		String type = null;
		type = filesystem.getMediaInfo( musicName ).getType();
		
		System.out.println("InputStream: " +  music + " : " +type );
		this.playMediaScreen = new PlayMediaScreen( midlet , music , type );
		this.musicPlayController = new MusicPlayController( midlet , playMediaScreen , musicName );
		this.playMediaScreen.setFormController( this.musicPlayController );
		
		ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGE_SCREEN );
	}
	
	public void showLastImage(){
		ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGE_SCREEN );	
		Display.getDisplay( midlet ).setCurrent( this.playMediaScreen );
	}
}