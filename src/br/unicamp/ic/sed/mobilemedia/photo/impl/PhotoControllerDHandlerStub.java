package br.unicamp.ic.sed.mobilemedia.photo.impl;

import br.unicamp.ic.sed.mobilemedia.main.spec.excep.VariabilityException;

class PhotoControllerDHandlerStub extends PhotoControllerDHandler implements IPhotoControllerDHandler{
		
	public void setCurrentStoreName( String currentStoreName ){
		throw new VariabilityException( "Photos");
	}
	
	public void setController( AbstractController controller ){
		throw new VariabilityException( "Photos");
	}
	
	
	public void showImage(String name ){
		throw new VariabilityException( "Photos");
	}
	

}
