package br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl;

import javax.microedition.lcdui.Command;

interface IBaseController {

	public void init();
	public boolean handleCommand( Command c );
	public boolean postCommand( Command c );
	
}
