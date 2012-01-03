package br.unicamp.ic.sed.mobilemedia.photo_vp_music;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoController;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhoto;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoListScreen;;

public aspect Variant_music {
	
	private String musicName = "Erro";
	private String albumName;
	
	pointcut getMusicName() : XPIPhotoController.getMediaName();
	
	String around( ) : getMusicName(){ 
		musicName = proceed();
		return musicName;
	}
	
	pointcut getAlbumName() : XPIPhotoController.getAlbumName();
	
	String around( ) : getAlbumName(){
		albumName = proceed();
		return albumName;
	}
	
	pointcut postCommand( Command c ) : XPIPhoto.postCommand( c ); 
	
	boolean around( Command c ) : postCommand( c ){
		boolean answer = proceed( c );
		
		MusicStub music = new MusicStub();
	
		if( music.postCommand( c , albumName , musicName ) )
			return true;
		else
			return answer;
	}
	
	private static String currentMedia = "Photos";
	
	public static void setCurrentMedia( String media ){
		currentMedia = media;
	}
	
	pointcut addCommand( String command , int type , int posi ) : 
		XPIPhotoListScreen.addCommand( command , type , posi );
	
	Command around( String command , int type , int posi ) : addCommand( command , type , posi ){
		if(currentMedia.equals( "Music" ) ){
			MusicStub music = new MusicStub();
			return music.addCommand( command , type , posi );
		}else
			return proceed( command , type , posi );
	}
	
}
