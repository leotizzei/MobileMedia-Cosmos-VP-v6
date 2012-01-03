package br.unicamp.ic.sed.mobilemedia.sorting.aspects;


import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;
import br.unicamp.ic.sed.mobilemedia.sorting.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.sorting.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.sorting.spec.excep.UnavailablePhotoAlbumException;


public aspect XPIFilesystemMgrSorting {

	public pointcut exceptionsInGetImage() : execution( protected IImageData br.unicamp.ic.sed.mobilemedia.sorting.aspects.FilesystemMgrSorting.getImage(String, String));
	
	declare soft : UnavailablePhotoAlbumException : exceptionsInGetImage(); 

	public pointcut getImage() : 
		call( public IImageData[] br.unicamp.ic.sed.mobilemedia.sorting.spec.req.IFilesystem.getImages ( String  ))
		&& withincode(protected IImageData br.unicamp.ic.sed.mobilemedia.sorting.aspects.FilesystemMgrSorting.getImage(String  , String ));
	
	
	

	//==============================

	public pointcut increaseNumberOfViews() : execution( 
			protected void br.unicamp.ic.sed.mobilemedia.sorting.aspects.FilesystemMgrSorting.increaseNumberOfViews(IImageData) );

	declare soft : InvalidImageDataException : increaseNumberOfViews();
	declare soft : PersistenceMechanismException  : increaseNumberOfViews();

	//==============================

	public pointcut getNumberOfViews() : call( protected int br.unicamp.ic.sed.mobilemedia.sorting.aspects.FilesystemMgrSorting.getNumberOfViews( String, int, int ));

	//==============================


	
}
