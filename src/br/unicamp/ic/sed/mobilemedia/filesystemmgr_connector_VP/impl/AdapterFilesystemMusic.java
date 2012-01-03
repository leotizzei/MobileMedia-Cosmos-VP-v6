package br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl;

import java.io.InputStream;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.music.spec.req.IFilesystemMusic;

class AdapterFilesystemMusic implements IFilesystemMusic{

	public InputStream getMusicFromRecordStore(String recordStore,String musicName) {
		IManager manager = ComponentFactory.createInstance();
			
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystemMedia filesystem =
			(br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystemMedia)
			manager.getRequiredInterface( "IFilesystem" );
		
		return filesystem.getMusicFromRecordStore(recordStore, musicName);
	
	}
	
	public IImageData getMediaInfo(String imageName)
	{
	
	
		IManager manager = ComponentFactory.createInstance();
			
		
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystemMedia filesystem =
			(br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystemMedia)
			manager.getRequiredInterface( "IFilesystem" );
		
		return filesystem.getImageInfo(imageName);
	}
	
}
