package br.unicamp.ic.sed.mobilemedia.photo_vp_sort_fav.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sorting.aspects.PhotoSorting;

public aspect SortingBinder extends PhotoSorting{

	public pointcut initMenu( List photoListScreen ):
		execution( public void SortingStub.initMenu( List ) )
		&& args( photoListScreen );
		
	public pointcut postCommand( Command c ):
		execution( public boolean SortingStub.postCommand( Command ) )
		&& args( c );
	
	public pointcut getImages( String recordName ):
		execution( public IImageData[] SortingStub.getImages( String ) )
		&& args( recordName );
	
	public pointcut setCurrentStoreName( String storeName ):
		execution( public boolean SortingStub.setCurrentStoreName( String ) )
		&& args( storeName );
	
}
