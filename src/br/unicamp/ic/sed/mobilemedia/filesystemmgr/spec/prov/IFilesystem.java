package br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public interface IFilesystem{
	

	public IImageData[] getImages ( String albumName ) ; 
	
	public void addNewPhotoToAlbum ( String imageName, String imagePath, String albumName ); 
	
	public void deleteImage ( String imageName, String albumName ); 
	
	public Image getImageFromRecordStore ( String albumName, String imageName ); 
		
	public String[] getAlbumNames (  ) ; 
	
	public void resetImageData (  ) ; 
	
	public void createNewPhotoAlbum ( String albumName ) ; 
	
	public void deletePhotoAlbum ( String albumName ) ;
		
	public boolean updateImageInfo(IImageData oldData, IImageData newData) ;

	//[cosmos][add Sce 2. Edit Label]
	public IImageData getImageInfo( String imageName ) ;
	
	
	/**
	 * This method was exposed to support the implementation of
	 * the copyPhoto aspect
	 * @param imageFile
	 * @return
	 * @throws InvalidImageDataException 
	 */
	public byte[] getBytesFromImageInfo(IImageData ii) ; 
	
	public void addImageData( String imageName , IImageData imageData , String albumName ) ;
	
}