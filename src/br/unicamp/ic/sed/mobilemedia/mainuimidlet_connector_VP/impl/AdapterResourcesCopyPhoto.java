package br.unicamp.ic.sed.mobilemedia.mainuimidlet_connector_VP.impl;

import javax.microedition.midlet.MIDlet;

import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.req.IMobileResources;

public class AdapterResourcesCopyPhoto implements IMobileResources {

	private IManager manager = ComponentFactory.createInstance();
	
	public MIDlet getMainMIDlet() {
		
		br.unicamp.ic.sed.mobilemedia.main.IMobileResources mobileResources = (br.unicamp.ic.sed.mobilemedia.main.IMobileResources) manager.getRequiredInterface("IMobileResources");
		return mobileResources.getMainMIDlet();
		
	}

	
	
}
