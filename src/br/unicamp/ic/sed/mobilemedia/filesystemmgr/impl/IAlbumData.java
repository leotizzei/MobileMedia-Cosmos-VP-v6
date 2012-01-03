package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import java.util.Hashtable;

import javax.microedition.lcdui.Image;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidPhotoAlbumNameException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.UnavailablePhotoAlbumException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public interface IAlbumData {

	/**
	 *  Load any photo albums that are currently defined in the record store
	 */
	public String[] getAlbumNames();

	/**
	 *  Define a new user photo album. This results in the creation of a new
	 *  RMS Record store.
	 * @throws PersistenceMechanismException 
	 * @throws InvalidPhotoAlbumNameException 
	 */
	public void createNewPhotoAlbum(String albumName)
			throws PersistenceMechanismException,
			InvalidPhotoAlbumNameException;

	public void deletePhotoAlbum(String albumName)
			throws PersistenceMechanismException;

	/**
	 *  Get a particular image (by name) from a photo album. The album name corresponds
	 *  to a record store.
	 * @throws ImageNotFoundException 
	 * @throws PersistenceMechanismException 
	 */
	public Image getImageFromRecordStore(String recordStore, String imageName)
			throws ImageNotFoundException, PersistenceMechanismException;

	public void addNewPhotoToAlbum(String label, String path, String album)
			throws InvalidImageDataException, PersistenceMechanismException;

	/**
	 *  Delete a photo from the photo album. This permanently deletes the image from the record store
	 * @throws ImageNotFoundException 
	 * @throws PersistenceMechanismException 
	 */
	public void deleteImage(String imageName, String storeName)
			throws PersistenceMechanismException, ImageNotFoundException;

	/**
	 *  Reset the image data for the application. This is a wrapper to the ImageAccessor.resetImageRecordStore
	 *  method. It is mainly used for testing purposes, to reset device data to the default album and photos.
	 * @throws PersistenceMechanismException 
	 * @throws InvalidImageDataException 
	 */
	public void resetImageData() throws PersistenceMechanismException;

	/**
	 * Get the hashtable that stores the image metadata in memory.
	 * @return Returns the imageInfoTable.
	 */
	public Hashtable getImageInfoTable();

	/**
	 * Update the hashtable that stores the image metadata in memory
	 * @param imageInfoTable
	 *            The imageInfoTable to set.
	 */
	public void setImageInfoTable(Hashtable imageInfoTable);

	//[cosmos][add Sce 2. Edit Label]
	public IImageData getImageInfo(String imageName)
			throws ImageNotFoundException;

	public boolean updateImageInfo(IImageData oldData, IImageData newData)
			throws InvalidImageDataException, PersistenceMechanismException;
	
	public IImageData[] getImages( String recordName ) throws UnavailablePhotoAlbumException;

	public void addImageData( String imageName , IImageData imageData , String albumName ) throws InvalidImageDataException, PersistenceMechanismException;
	 
}