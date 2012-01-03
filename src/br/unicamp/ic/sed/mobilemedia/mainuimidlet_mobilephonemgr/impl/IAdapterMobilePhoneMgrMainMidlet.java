package br.unicamp.ic.sed.mobilemedia.mainuimidlet_mobilephonemgr.impl;

import javax.microedition.midlet.MIDlet;


import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IMobileResources;

public class IAdapterMobilePhoneMgrMainMidlet implements IMobileResources {

	private IManager manager;
	
	IAdapterMobilePhoneMgrMainMidlet(IManager mgr) {
		this.manager = mgr;
	}
	
	public MIDlet getMainMIDlet() {
		
		br.unicamp.ic.sed.mobilemedia.main.IMobileResources mobileResources = (br.unicamp.ic.sed.mobilemedia.main.IMobileResources) manager.getRequiredInterface("IMobileResources");
		return mobileResources.getMainMIDlet();
		
	}

	public void destroyApp(boolean unconditional) {
	
		br.unicamp.ic.sed.mobilemedia.main.IMobileResources iMobileResources = (br.unicamp.ic.sed.mobilemedia.main.IMobileResources)manager.getRequiredInterface("IMobileResources");
		iMobileResources.destroyApp(unconditional);
		
	}	
	
}
