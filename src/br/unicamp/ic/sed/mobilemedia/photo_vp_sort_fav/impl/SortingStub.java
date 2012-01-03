package br.unicamp.ic.sed.mobilemedia.photo_vp_sort_fav.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

class SortingStub {
	
	public void initMenu( List photoListScreen ){}
	
	public IImageData[] returnGetImages;
	
	public IImageData[] getImages( String recordName ){
		return returnGetImages;
	}

	public boolean postCommand( Command c ){
		return false;
	}
	
	public boolean setCurrentStoreName( String storeName ){
		return false;
	}
	
}
