package br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IFilesystem;

public class AdapterFilesystemSms implements IFilesystem {

	public String[] getAlbumNames() {
		IManager manager = ComponentFactory.createInstance();
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem
			= (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)
				manager.getRequiredInterface("IFilesystem");
		
		return filesystem.getAlbumNames();
	}

	public IImageData getImageInfo(String imageName)
	{		
			IManager manager = ComponentFactory.createInstance();
			br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem
				= (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)
					manager.getRequiredInterface("IFilesystem");
			
			return filesystem.getImageInfo(imageName);
		
		
	}

	
	public byte[] getBytesFromImageInfo(IImageData ii)
	{
			IManager manager = ComponentFactory.createInstance();
			br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem
				= (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)
					manager.getRequiredInterface("IFilesystem");
			
			return filesystem.getBytesFromImageInfo(ii);
		
	}

}