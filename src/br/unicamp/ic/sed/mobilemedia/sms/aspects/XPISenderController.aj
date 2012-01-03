package br.unicamp.ic.sed.mobilemedia.sms.aspects;

import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;


public aspect XPISenderController {
	
	public pointcut getImageInfoToSend(): call(public IImageData IFilesystem.getImageInfo(String));
	
}
