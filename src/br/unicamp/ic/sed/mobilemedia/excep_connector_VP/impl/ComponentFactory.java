package br.unicamp.ic.sed.mobilemedia.excep_connector_VP.impl;

public class ComponentFactory {

	private static IManager mgr = null;
	
	public static IManager createInstance(){
		if(mgr == null){
			mgr = (IManager) new Manager();
		}
		return mgr;
	}
	
}
