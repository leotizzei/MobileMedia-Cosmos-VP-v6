package br.unicamp.ic.sed.mobilemedia.sorting.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import br.unicamp.ic.sed.mobilemedia.sorting.spec.prov.IManager;



class Manager implements IManager{

	Hashtable requiredInterfaces = new Hashtable();
	Hashtable providedInterfaces = new Hashtable();


	Manager(){
		

	}

	public String[] getProvidedInterfaces(){

		return convertListToArray(providedInterfaces.keys());
	}

	public String[] getRequiredInterfaces(){

		return convertListToArray(requiredInterfaces.keys());
	}

	public void init(){}

	public Object getProvidedInterface(String name){


		return this.providedInterfaces.get( name );
	}

	public void setRequiredInterface(String name, Object facade){
		requiredInterfaces.put(name,facade);
	}

	public Object getRequiredInterface(String name){
			
		return this.requiredInterfaces.get(name);
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



