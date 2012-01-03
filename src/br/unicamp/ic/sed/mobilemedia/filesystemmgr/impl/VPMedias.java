package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.UnavailablePhotoAlbumException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public class VPMedias implements IAlbumData{	
	
	private AlbumData albumData = new AlbumData();
	private MusicAlbumData musicAlbumData = new MusicAlbumData();
	
	private static String currentMedia = "Photos";
	
	public static void setMedia( String media ){
		System.out.println("========Mudou: " + media );
		currentMedia = media;
	}
	
	public void addNewPhotoToAlbum(String label, String path, String album)
			throws InvalidImageDataException, PersistenceMechanismException {
	
		if( currentMedia.equals( "Photos" ) )
			albumData.addNewPhotoToAlbum(label, path, album);
		else
			musicAlbumData.addNewPhotoToAlbum(label, path, album);
		
		
	}

	public void createNewPhotoAlbum(String albumName)
			throws PersistenceMechanismException,
			InvalidPhotoAlbumNameException {
		
		if( currentMedia.equals( "Photos" ) )
			albumData.createNewPhotoAlbum(albumName);
		else
			musicAlbumData.createNewPhotoAlbum(albumName);
		
	}

	public void deleteImage(String imageName, String storeName)
			throws PersistenceMechanismException, ImageNotFoundException {
		
		if( currentMedia.equals( "Photos" ) )
			albumData.deleteImage(imageName, storeName);
		else
			musicAlbumData.deleteImage(imageName, storeName);
		
	}

	public void deletePhotoAlbum(String albumName)
			throws PersistenceMechanismException {
		
		if( currentMedia.equals( "Photos" ) )
			albumData.deletePhotoAlbum(albumName);
		else
			musicAlbumData.deletePhotoAlbum(albumName);
		
	}

	public String[] getAlbumNames() {
		if( currentMedia.equals( "Photos" ) )
			return albumData.getAlbumNames();
		else
			return musicAlbumData.getAlbumNames();		
	}

	public Image getImageFromRecordStore(String recordStore, String imageName)
			throws ImageNotFoundException, PersistenceMechanismException {
		
		if( currentMedia.equals( "Photos" ) ){
			return albumData.getImageFromRecordStore(recordStore, imageName);
		}
		return null;
	}

	public IImageData getImageInfo(String imageName)
			throws ImageNotFoundException {
		
		if( currentMedia.equals( "Photos" ) )
			return albumData.getImageInfo(imageName);
		else
			return musicAlbumData.getImageInfo(imageName);
				
	}

	public Hashtable getImageInfoTable() {
		if( currentMedia.equals( "Photos" ) )
			return albumData.getImageInfoTable();
		else
			return musicAlbumData.getImageInfoTable();
				
	}

	public void resetImageData() throws PersistenceMechanismException {
		
		if( currentMedia.equals( "Photos" ) )
			albumData.resetImageData();
		else
			musicAlbumData.resetImageData();
				
	}

	public void setImageInfoTable(Hashtable imageInfoTable) {
		
		if( currentMedia.equals( "Photos" ) )
			albumData.setImageInfoTable(imageInfoTable);
		else
			musicAlbumData.setImageInfoTable(imageInfoTable);				
				
	}

	public boolean updateImageInfo(IImageData oldData, IImageData newData)
			throws InvalidImageDataException, PersistenceMechanismException {

		if( currentMedia.equals( "Photos" ) )
			return albumData.updateImageInfo(oldData, newData);
		else
			return musicAlbumData.updateImageInfo(oldData, newData);
				
	}
	
	public IImageData[] getImages( String recordName ) throws UnavailablePhotoAlbumException{
		if( currentMedia.equals( "Photos" ) )
			return albumData.getImages( recordName );
		else
			return musicAlbumData.getImages( recordName );
	}
	
	public void addImageData( String imageName , IImageData imageData , String albumName ) throws InvalidImageDataException, PersistenceMechanismException{
		if( currentMedia.equals( "Photos" ) )
			albumData.addImageData(imageName, imageData, albumName);
		else
			musicAlbumData.addImageData(imageName, imageData, albumName);
	}
}
