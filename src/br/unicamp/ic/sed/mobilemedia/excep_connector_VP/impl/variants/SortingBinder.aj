package br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.variants;

import br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.ExceptionAdapter;

import br.unicamp.ic.sed.mobilemedia.sorting.aspects.XPIFilesystemMgrSorting;

public aspect SortingBinder extends ExceptionAdapter {

	public pointcut handleException() : XPIFilesystemMgrSorting.exceptionsInGetImage()
										|| XPIFilesystemMgrSorting.increaseNumberOfViews();
	
}
