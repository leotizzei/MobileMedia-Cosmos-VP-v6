package br.unicamp.ic.sed.mobilemedia.photo.impl;


public interface IPhotoControllerDHandler {

	public void setCurrentStoreName( String currentStoreName );
	public void setController( AbstractController controller );
	public void showImage(String name );
	
}
