package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;


public class ObjectFactory {
	
	private static IAlbumData vpMedias = new VPMedias();
	
	public static IAlbumData getIAlbumData(){
		return vpMedias;
	}
	
}
