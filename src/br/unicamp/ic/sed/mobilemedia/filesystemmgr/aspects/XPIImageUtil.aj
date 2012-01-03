package br.unicamp.ic.sed.mobilemedia.filesystemmgr.aspects;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
public aspect XPIImageUtil {

	/*************************************************************************************************************/
	//=========ImageUtil============
	
	public pointcut readImageAsByteArray(String imageFile):
		execution(public byte[] br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.ImageUtil.readImageAsByteArray(String)) 
		&& args(imageFile);
	
	public pointcut getImageInfoFromBytes(): 
		execution(public IImageData br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.ImageUtil.getImageInfoFromBytes(byte[]));
	
	public pointcut getBytesFromImageInfo(): 
		execution(public byte[] br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.ImageUtil.getBytesFromImageInfo(IImageData));

	public pointcut createImageData(int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes, int endIndex ):
		call(public IImageData br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.ImageUtil.createImageData(int, String, String , byte[], int))
		&& args( foreignRecordId, parentAlbumName,imageLabel , bytes, endIndex );
	
}
