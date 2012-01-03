
package br.unicamp.ic.sed.mobilemedia.filesystemmgr_mobilephonemgr.impl;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IFilesystem;

class IFileSystemAdapter implements IFilesystem{

	private Manager manager;

	public IFileSystemAdapter(Manager mgr) {
		this.manager = mgr; 
	}
 
	public String[] getAlbumNames (  ) {

		br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem filesystem;
		filesystem = (br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IFilesystem)manager.getRequiredInterface("IFilesystem");		
	
		return filesystem.getAlbumNames();
	}

	
}