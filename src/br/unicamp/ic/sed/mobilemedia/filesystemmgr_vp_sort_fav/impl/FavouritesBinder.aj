package br.unicamp.ic.sed.mobilemedia.filesystemmgr_vp_sort_fav.impl;

import br.unicamp.ic.sed.mobilemedia.favourites.aspects.FilesystemMgrFavourites;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect FavouritesBinder extends FilesystemMgrFavourites{
	
	public pointcut getBytesFromImageInfo(IImageData ii):
		execution( public byte[] FavouritesStub.getBytesFromImageInfo( IImageData ))
		&& args( ii );

	public pointcut createImageData(int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex  ):
		execution( public IImageData FavouritesStub.createImageData( int, String , String , byte[] , int ))
		&& args( foreignRecordId , parentAlbumName , imageLabel , bytes , endIndex );
	
}