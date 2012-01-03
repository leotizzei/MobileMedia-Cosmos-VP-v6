package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl;

import javax.microedition.midlet.MIDlet;

public aspect VariantFactory {

	public pointcut constructBaseController( MIDlet midlet ) : 
		call( public BaseController.new(MIDlet) )
		&& args( midlet )
		&& within( !VariantFactory );
	
	private static boolean musicFeature = false;
	private static boolean photoFeature = false;
	
	public static void setMusicFeature( boolean feature ){
		musicFeature = feature;
	}
	
	public static void setPhotoFeature( boolean feature ){
		photoFeature = feature;
	}
	
	private static int mediasCount = 0 ;
	
	BaseController around( MIDlet midlet ) : constructBaseController( midlet ){
		mediasCount = 0;
		
		if( photoFeature ) mediasCount++;
		if( musicFeature ) mediasCount++;
		
		if( mediasCount == 1 )
			return new BaseController( midlet );
		else
			return new MediasBaseController( midlet );
	}
	
	public static String[] getMedias(){
		String[] medias = new String[ mediasCount ];
		
		int count = 0;
		
		if( photoFeature ) medias[ count++ ] = "Photos";
		if( musicFeature ) medias[ count++ ] = "Music";
		
		return medias;
	}
	
}
