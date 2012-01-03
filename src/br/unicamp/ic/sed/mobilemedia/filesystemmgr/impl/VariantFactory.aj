package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.IFilesystemMediaFacade;

public aspect VariantFactory {
	
	private static boolean musicFeature = false;
	private MusicAlbumData musicAlbumData = new MusicAlbumData();
	
	public static void setMusicFeature( boolean feature ){
		musicFeature = feature;
	}
	
	pointcut constructMusicAlbumData() : 
		call( public MusicAlbumData.new() )
		&& within( !VariantFactory );
	
	MusicAlbumData around() : constructMusicAlbumData(){
		if( musicFeature )
			return musicAlbumData;
		else
			throw new RuntimeException( "Optional variant not bound" );
	}
	
	//---------------------------------------------------------------------
	
	pointcut constructIFilesystemFacade() :
		call( public IFilesystemFacade.new() )
		&& within( !VariantFactory );

	IFilesystemFacade around( ) : constructIFilesystemFacade(){
		return new IFilesystemMediaFacade();
	}
	
	//---------------------------------------------------------------------	
}
