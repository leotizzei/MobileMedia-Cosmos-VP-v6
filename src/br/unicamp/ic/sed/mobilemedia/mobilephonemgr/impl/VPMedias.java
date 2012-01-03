package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl;

import javax.microedition.lcdui.Command;
import javax.microedition.midlet.MIDlet;

public class VPMedias implements IBaseController{
	
	private MIDlet midlet;
	
	public VPMedias( MIDlet midlet ){
		this.midlet = midlet;
	}
	
	public boolean handleCommand(Command c) {
		return new BaseController( midlet ).handleCommand( c );
	}

	public void init() {
		new BaseController( midlet ).init();
		
	}

	public boolean postCommand(Command c) {
		return new BaseController( midlet ).postCommand( c );
	}

}
