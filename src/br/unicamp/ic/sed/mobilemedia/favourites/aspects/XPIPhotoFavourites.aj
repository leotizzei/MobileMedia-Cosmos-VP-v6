package br.unicamp.ic.sed.mobilemedia.favourites.aspects;

import br.unicamp.ic.sed.mobilemedia.favourites.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.favourites.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.favourites.spec.excep.UnavailablePhotoAlbumException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect XPIPhotoFavourites {
	
	public pointcut getImages() : call(IImageData[] br.unicamp.ic.sed.mobilemedia.favourites.spec.req.IFilesystem.getImages(String))
	&&withincode(void br.unicamp.ic.sed.mobilemedia.favourites.aspects.PhotoFavourites.setFavourite(String)); 
	
	declare soft:UnavailablePhotoAlbumException : getImages();
		

	public pointcut updateImageInfo() : call(boolean br.unicamp.ic.sed.mobilemedia.favourites.spec.req.IFilesystem.updateImageInfo(IImageData , IImageData ))
	&&withincode(void br.unicamp.ic.sed.mobilemedia.favourites.aspects.PhotoFavourites.setFavourite(String)); 
	
	declare soft:PersistenceMechanismException: updateImageInfo();
		
	declare soft:InvalidImageDataException:updateImageInfo();
}
