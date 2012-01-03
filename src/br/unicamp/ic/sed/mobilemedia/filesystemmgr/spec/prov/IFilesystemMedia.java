package br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov;

import java.io.InputStream;

public interface IFilesystemMedia extends IFilesystem{

	public InputStream getMusicFromRecordStore(String recordStore, String musicName);
}
