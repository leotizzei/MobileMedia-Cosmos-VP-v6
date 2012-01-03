package br.unicamp.ic.sed.mobilemedia.photo_vp_sort_fav.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhoto;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoListController;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoListScreen;
import br.unicamp.ic.sed.mobilemedia.photo.aspects.XPIPhotoController;


public privileged aspect Variant_sort_fav {

	private FavouritesStub favourites = new FavouritesStub();
	private SortingStub sorting = new SortingStub();
	
	public pointcut initMenu( List photoListScreen ):
		XPIPhotoListScreen.initMenu(photoListScreen);
	
	void around( List photoListScreen ) : initMenu( photoListScreen ){
		proceed( photoListScreen );
		sorting.initMenu( photoListScreen );
		favourites.initMenu( photoListScreen );
	}
	
	
	
	public pointcut getImages(String recordName):
		XPIPhotoListController.getImages(recordName);
	
	IImageData[] around( String recordName ) : getImages( recordName ){
		sorting.returnGetImages = proceed( recordName );
		
		favourites.returnGetImages = sorting.getImages( recordName );
		return favourites.getImages( recordName );
	}
	
	
	
	pointcut getImageName() : XPIPhotoController.getMediaName();
	
	private String imageSelected = null;
	
	String around( ) : getImageName(){ 
		imageSelected = proceed();
		return imageSelected; 
	}
	
	public pointcut postCommand(Command c):
		XPIPhoto.postCommand(c);
	
	boolean around( Command c ): postCommand( c ){
			
		if( c.getLabel().equals( "Set Favourite") ){
			proceed( c );
			favourites.postCommand( c , imageSelected );
			return true;
		}
		else{
			if( sorting.postCommand( c ) || favourites.postCommand( c , imageSelected ) ){ 
				c = new Command("Select", Command.ITEM, 1);
			}
			return proceed( c );
		}
	}
	
	
	
	public pointcut setCurrentStoreName(String storeName):
		XPIPhotoListController.setCurrentStoreName(storeName);

	void around( String storeName ) : setCurrentStoreName( storeName ){
		if( sorting.setCurrentStoreName( storeName ) )
			if( favourites.setCurrentStoreName( storeName ) )
				proceed( storeName );
	}
}
