package br.unicamp.ic.sed.mobilemedia.mainuimidlet_connector_VP.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Variant {
	
	private Hashtable providedInterfaces = new Hashtable();
	
	public Variant(){
		providedInterfaces.put("IMobileResourcesMusic",new AdapterResourcesMusic() ) ;
		providedInterfaces.put("IMobileResourcesSms",new AdapterResourcesSms() ) ;
		providedInterfaces.put("IMobileResourcesCopyPhoto",new AdapterResourcesCopyPhoto() ) ;
	}
	
	public Object getProvidedInterface(String interfaceName) {
		return providedInterfaces.get( interfaceName );
	}

	public String[] getProvidedInterfaces() {
		Enumeration stringEnum = providedInterfaces.keys();
		Vector stringVector = new Vector();
		for (Enumeration iter = stringEnum; iter.hasMoreElements();) {
			String element = (String) iter.nextElement();
			stringVector.addElement(element);
		}
		
		String[] stringArray = new String[stringVector.size()];
		for (int i=0; i < stringVector.size(); i++){
			stringArray[i] = (String) stringVector.elementAt(i);
		}
		return stringArray;
	}
}
