package br.unicamp.ic.sed.mobilemedia.sorting.spec.req;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public interface IFilesystem{
	

	public IImageData[] getImages ( String albumName ); 
	
	//public void addNewPhotoToAlbum ( String imageName, String imagePath, String albumName ) throws InvalidImageDataException, PersistenceMechanismException; 
	
	//public void deleteImage ( String imageName, String albumName ) throws PersistenceMechanismException, ImageNotFoundException; 
	
	//public Image getImageFromRecordStore ( String albumName, String imageName ) throws ImageNotFoundException, PersistenceMechanismException; 
		
	//public String[] getAlbumNames (  ) ; 
	
	//public void resetImageData (  ) throws PersistenceMechanismException /*throws PersistenceMechanismException, InvalidImageDataException*/; 
	
	//public void createNewPhotoAlbum ( String albumName ) throws PersistenceMechanismException, InvalidPhotoAlbumNameException; 
	
	//public void deletePhotoAlbum ( String albumName ) throws PersistenceMechanismException;
		
	public boolean updateImageInfo(IImageData oldData, IImageData newData);

	//[cosmos][add Sce 2. Edit Label]
	//public IImageData getImageInfo( String imageName ) throws ImageNotFoundException, NullAlbumDataReference;

}