package br.unicamp.ic.sed.mobilemedia.filesystemmgr.aspects;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import javax.microedition.lcdui.Image;
import java.io.InputStream;

public aspect XPIIFilesystem {

	/***************************************************************/
	public pointcut getBytesFromImageInfo(IImageData ii):
		execution(byte[] br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem.getBytesFromImageInfo(IImageData)) && args(ii);

	
	/***************************************************************/
	public pointcut updateCounter(String albumName, String imageName):
		(execution(public Image br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem.getImageFromRecordStore(String , String ))
		||execution(public InputStream br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystemMedia.getMusicFromRecordStore(String , String )))
		&& args(albumName,imageName) ;

	/***************************************************************************************/
	
}
