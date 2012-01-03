package br.unicamp.ic.sed.mobilemedia.filesystemmgr_vp_sort_fav.impl;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.aspects.XPIImageUtil;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.aspects.XPIIFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public privileged aspect Variant_sort_fav {
	
	public pointcut getBytesFromImageInfo(IImageData ii):
		XPIIFilesystem.getBytesFromImageInfo(ii);

	byte[] around( IImageData ii ) : getBytesFromImageInfo( ii ){
		SortingStub sorting = new SortingStub();
		FavouritesStub favourites = new FavouritesStub();
		
		sorting.returnGetBytesFromImageInfo = proceed( ii );
		favourites.returnGetBytesFromImageInfo = sorting.getBytesFromImageInfo( ii );
		
		return favourites.getBytesFromImageInfo( ii );
	}
	
	
	public pointcut createImageData(int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex  ):
		XPIImageUtil.createImageData(foreignRecordId, parentAlbumName,imageLabel , bytes , endIndex );
	
	IImageData around( int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex   ) :  
		createImageData( foreignRecordId , parentAlbumName, imageLabel , bytes , endIndex ){
		
		SortingStub sorting = new SortingStub();
		FavouritesStub favourites = new FavouritesStub();
		
		sorting.returnCreateImageData = proceed( foreignRecordId , parentAlbumName, imageLabel , bytes , endIndex );
		favourites.returnCreateImageData = sorting.createImageData( foreignRecordId , parentAlbumName, imageLabel , bytes , endIndex );
		
		return favourites.createImageData( foreignRecordId , parentAlbumName, imageLabel , bytes , endIndex );
	}
	
	public pointcut updateCounter(String albumName, String imageName):
		XPIIFilesystem.updateCounter( albumName , imageName );
	
	after( String albumName , String imageName ): updateCounter( albumName , imageName ){

		SortingStub sorting = new SortingStub();
		
		sorting.updateCounter( albumName , imageName );
	}
}
