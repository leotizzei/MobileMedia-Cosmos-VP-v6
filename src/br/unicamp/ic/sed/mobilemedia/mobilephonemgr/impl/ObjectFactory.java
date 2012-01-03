package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl;

import javax.microedition.midlet.MIDlet;

public class ObjectFactory {

	public static IBaseController getIBaseController( MIDlet midlet ){
		return new VPMedias( midlet );
	}
	
}
