package br.unicamp.ic.sed.mobilemedia.music.spec.req;

import java.io.InputStream;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public interface IFilesystemMusic{

	public InputStream getMusicFromRecordStore(String recordStore, String musicName) ;
	public IImageData getMediaInfo( String imageName ) ;
}
