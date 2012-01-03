package br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl;

import br.unicamp.ic.sed.mobilemedia.favourites.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

class AdapterFilesystemFavourites implements IFilesystem {

	private IManager manager = ComponentFactory.createInstance();
	
	public IImageData[] getImages(String albumName)
	{
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem) manager.getRequiredInterface("IFilesystem");
	
		return filesystem.getImages(albumName);
		
	}

	
	public boolean updateImageInfo(IImageData oldData, IImageData newData)
	{
		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem) manager.getRequiredInterface("IFilesystem");
	
		return filesystem.updateImageInfo(oldData, newData);
		
		
	}

}
