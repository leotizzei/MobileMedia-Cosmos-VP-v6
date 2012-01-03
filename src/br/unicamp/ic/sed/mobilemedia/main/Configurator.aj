package br.unicamp.ic.sed.mobilemedia.main;

public aspect Configurator {

	public static void configureComponentVP(){
		br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl.VariantFactory.setMusicFeature( true );
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.VariantFactory.setMusicFeature( true );
		
		br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl.VariantFactory.setPhotoFeature( true );
		br.unicamp.ic.sed.mobilemedia.photo.impl.VariantFactory.setPhotoFeature( true );
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.VPMedias.setMedia( "Music" );
		br.unicamp.ic.sed.mobilemedia.photo_vp_music.Variant_music.setCurrentMedia( "Music" );
		
	}
	
	pointcut setCurrentMedia( String media ) : 
		call( private void br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl.SelectMediaController.startAlbum(String))
		&& args( media );
	
	before( String media ) : setCurrentMedia( media ){
		System.out.println("Mudando de Media: " + media );
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.VPMedias.setMedia( media );
		br.unicamp.ic.sed.mobilemedia.photo_vp_music.Variant_music.setCurrentMedia( media );
	}
	
	
}
