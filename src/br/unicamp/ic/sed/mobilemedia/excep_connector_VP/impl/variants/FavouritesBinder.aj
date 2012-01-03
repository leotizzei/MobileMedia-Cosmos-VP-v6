package br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.variants;

import br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.ExceptionAdapter;

import br.unicamp.ic.sed.mobilemedia.favourites.aspects.XPIPhotoFavourites;

public aspect FavouritesBinder extends ExceptionAdapter{

	public pointcut handleException() : XPIPhotoFavourites.getImages()
										|| XPIPhotoFavourites.updateImageInfo();
	
}
