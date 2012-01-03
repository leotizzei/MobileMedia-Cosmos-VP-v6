package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public class MusicAlbumData extends AlbumData {
	
	public MusicAlbumData() {
		imageAccessor = new MusicMediaAccessor();
	}
	
	public InputStream getMusicFromRecordStore(String recordStore, String musicName) throws ImageNotFoundException, PersistenceMechanismException {

		IImageData mediaInfo = null;
		mediaInfo = imageAccessor.getImageInfo(musicName);
		//Find the record ID and store name of the image to retrieve
		int mediaId = mediaInfo.getForeignRecordId();
		String album = mediaInfo.getParentAlbumName();
		//Now, load the image (on demand) from RMS and cache it in the hashtable
		byte[] musicData = imageAccessor.loadImageBytesFromRMS(album,musicName, mediaId);
		return new ByteArrayInputStream(musicData);

	}
	
	public String getMusicType(String musicName) throws ImageNotFoundException {
		
		IImageData music = this.getImageInfo(musicName);
		return music.getType();
	}
	
	public void setMusicType( String musicName , String type ) throws ImageNotFoundException{
		IImageData music = this.getImageInfo(musicName);
		music.setType( type );
	}
}
