package br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.variants;

import br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl.ExceptionAdapter;

import br.unicamp.ic.sed.mobilemedia.copyphoto.aspects.CopyPhoto_Photo;

public aspect CopyphotoBinder extends ExceptionAdapter {

	public pointcut handleException() : CopyPhoto_Photo.handleException();
	
}
