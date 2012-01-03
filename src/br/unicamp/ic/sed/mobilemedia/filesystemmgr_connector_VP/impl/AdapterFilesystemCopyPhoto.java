
package br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl;

import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

class AdapterFilesystemCopyPhoto implements IFilesystem{

	private IManager manager = ComponentFactory.createInstance();
 
	public IImageData[] getImages ( String albumName ) {

		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		
		return filesystem.getImages(albumName);
		
	}


	public byte[] getBytesFromImageInfo(IImageData ii)
{

		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");

		return filesystem.getBytesFromImageInfo(ii);
	}

	
	public IImageData getImageInfo(String imageName)
	{
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");

		return filesystem.getImageInfo(imageName);
	}

	 
	public void addImageData(String imageName, IImageData imageData,
			String albumName){


		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");
		
		filesystem.addImageData(imageName, imageData, albumName);
		
		
	} 


}