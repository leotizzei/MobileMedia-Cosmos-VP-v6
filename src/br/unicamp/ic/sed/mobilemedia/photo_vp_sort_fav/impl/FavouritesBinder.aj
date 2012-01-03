package br.unicamp.ic.sed.mobilemedia.photo_vp_sort_fav.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

import br.unicamp.ic.sed.mobilemedia.favourites.aspects.PhotoFavourites;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect FavouritesBinder extends PhotoFavourites{

	public pointcut initMenu( List photoListScreen ):
		execution( public void FavouritesStub.initMenu( List ) )
		&& args( photoListScreen );
		
	public pointcut postCommand( Command c , String imageName ):
		execution( public boolean FavouritesStub.postCommand( Command , String ) )
		&& args( c , imageName );
	
	public pointcut getImages( String recordName ):
		execution( public IImageData[] FavouritesStub.getImages( String ) )
		&& args( recordName );
	
	public pointcut setCurrentStoreName( String storeName ):
		execution( public boolean FavouritesStub.setCurrentStoreName( String ) )
		&& args( storeName );
	
}
