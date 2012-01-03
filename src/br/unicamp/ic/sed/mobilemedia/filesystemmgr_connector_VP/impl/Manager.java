package br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl;

import java.util.*;

class Manager implements IManager{

	private Hashtable requiredInterfaces = new Hashtable();

	public Manager() {
	}
	
	public String[] getProvidedInterfaces(){
		return null;
	}
	
	public String[] getRequiredInterfaces(){
	
		return convertListToArray(requiredInterfaces.keys());
	}
	
	public Object getProvidedInterface(String name){
		return null;
	}
	
	public void setRequiredInterface(String name, Object facade){
		
		requiredInterfaces.put(name,facade);
	}
	
	public Object getRequiredInterface(String name){
	   return requiredInterfaces.get(name);
	}
	
	private String[] convertListToArray(Enumeration stringEnum){
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



