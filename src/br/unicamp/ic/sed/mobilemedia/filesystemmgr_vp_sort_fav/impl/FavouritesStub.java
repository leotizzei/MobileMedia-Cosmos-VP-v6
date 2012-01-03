package br.unicamp.ic.sed.mobilemedia.filesystemmgr_vp_sort_fav.impl;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

class FavouritesStub {

public byte[] returnGetBytesFromImageInfo;
	
	public byte[] getBytesFromImageInfo( IImageData imageData ){
		return returnGetBytesFromImageInfo;
	}
	
	public IImageData returnCreateImageData;
	
	public IImageData createImageData( int foreignRecordId , String parentAlbumName , String imageLabel , byte[] bytes , int endIndex ){
		return returnCreateImageData;
	}
	
}
