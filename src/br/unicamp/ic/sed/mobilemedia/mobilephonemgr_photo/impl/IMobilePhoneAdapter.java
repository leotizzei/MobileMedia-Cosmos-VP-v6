   
package br.unicamp.ic.sed.mobilemedia.mobilephonemgr_photo.impl;

import javax.microedition.lcdui.Command;


import br.unicamp.ic.sed.mobilemedia.photo.spec.req.IMobilePhone;

class IMobilePhoneAdapter implements IMobilePhone{
	
	public boolean postCommand ( Command command ) {
		
		br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IMobilePhone mobilePhone;
		
		IManager manager = ComponentFactory.createInstance();
		mobilePhone = (br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IMobilePhone)manager.getRequiredInterface("IMobilePhone");
		
		
		return mobilePhone.postCommand(command);
	}

	
	

}