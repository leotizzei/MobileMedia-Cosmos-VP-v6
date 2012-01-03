package br.unicamp.ic.sed.mobilemedia.photo.impl;


public aspect VariantFactory {

	pointcut constructPhotoControllerHandler() : 
		call( public PhotoControllerDHandler.new() )
		&& within( !VariantFactory );
	
	private static boolean photoFeature = false;
	
	public static void setPhotoFeature( boolean feature ){
		photoFeature = feature;
	}
	
	PhotoControllerDHandler around() : constructPhotoControllerHandler(){
		if( photoFeature )
			return new PhotoControllerDHandler();
		else
			return new PhotoControllerDHandlerStub();
		
	}
	
}
