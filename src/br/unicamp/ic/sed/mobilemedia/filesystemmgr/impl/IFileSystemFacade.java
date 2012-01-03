   
package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImagePathNotValidException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageFormatException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

class IFilesystemFacade implements IFilesystem{

	private IAlbumData albumData =  ObjectFactory.getIAlbumData();
	private ImageUtil imageUtil;
	
	public IFilesystemFacade(){
		this.imageUtil = new ImageUtil();
	}
	
	public IImageData[] getImages(	String albumName ) {
		return albumData.getImages(albumName);
	} 
	
	public void addNewPhotoToAlbum ( String imageName, String imagePath, String albumName ){
		albumData.addNewPhotoToAlbum(imageName, imagePath, albumName);
	} 
	
	public void deleteImage ( String imageName, String albumName ){
		System.out.println("[IFilesystemFacade:deleteImage("+imageName+","+albumName+")");
		albumData.deleteImage(imageName, albumName);
	} 
	
	public Image getImageFromRecordStore ( String albumName, String imageName ){
		return albumData.getImageFromRecordStore(albumName, imageName);
	} 
	
	public String[] getAlbumNames (  )/* throws InvalidImageDataException, PersistenceMechanismException*/ {
		return albumData.getAlbumNames();
	} 
	
	public void resetImageData (  ){
		albumData.resetImageData();	
	} 
	
	public void createNewPhotoAlbum ( String albumName ) {
		albumData.createNewPhotoAlbum(albumName);		
	} 
	
	public void deletePhotoAlbum ( String albumName ) {
		albumData.deletePhotoAlbum(albumName);	
	}

	public boolean updateImageInfo(IImageData oldData, IImageData newData) {
		return this.albumData.updateImageInfo(oldData, newData);
	}

	
	public IImageData getImageInfo(String imageName) {
		return albumData.getImageInfo(imageName);
	}
	
	/**
	 * This method was exposed to support the implementation of
	 * the copyPhoto aspect
	 * @param imageFile
	 * @return
	 * @throws InvalidImageFormatException 
	 * @throws ImagePathNotValidException 
	 */
	public byte[] readImageAsByteArray(String imageFile) {
		return this.imageUtil.readImageAsByteArray(imageFile);
	}

	
	/**
	 * This method was exposed to support the implementation of
	 * the copyPhoto aspect
	 * @param imageFile
	 * @return
	 * @throws InvalidImageDataException 
	 */
	public byte[] getBytesFromImageInfo(IImageData ii) {
		return this.imageUtil.getBytesFromImageInfo(ii);
	}

	public void addImageData(String imageName, IImageData imageData, String albumName)  {
		this.albumData.addImageData(imageName, imageData, albumName); 
	}
}