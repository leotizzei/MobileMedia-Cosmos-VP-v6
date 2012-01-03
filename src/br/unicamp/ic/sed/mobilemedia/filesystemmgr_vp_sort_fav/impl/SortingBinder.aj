package br.unicamp.ic.sed.mobilemedia.filesystemmgr_vp_sort_fav.impl;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sorting.aspects.FilesystemMgrSorting;

public privileged aspect SortingBinder extends FilesystemMgrSorting {
	
	public pointcut getBytesFromImageInfo(IImageData ii):
		execution( public byte[] SortingStub.getBytesFromImageInfo( IImageData ))
		&& args( ii );

	public pointcut createImageData(int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex  ):
		execution( public IImageData SortingStub.createImageData( int, String , String , byte[] , int ))
		&& args( foreignRecordId , parentAlbumName , imageLabel , bytes , endIndex );
	
	public pointcut updateCounter(String albumName, String imageName):
		execution( public void SortingStub.updateCounter( String , String ))
		&& args( albumName , imageName );

}