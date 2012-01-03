package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import java.io.InputStream;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystemMedia;

public class IFilesystemMediaFacade extends IFilesystemFacade implements IFilesystemMedia{

	public IFilesystemMediaFacade(){
		super();
	}
	
	public InputStream getMusicFromRecordStore(String recordStore, String musicName) {
		
		return new MusicAlbumData().getMusicFromRecordStore(recordStore, musicName);
		
	}

}
