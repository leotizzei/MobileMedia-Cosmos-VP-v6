package br.unicamp.ic.sed.mobilemedia.mainuimidlet_connector_VP.impl;

public aspect VariantBinder {

	private Variant variant = new Variant();
	
	pointcut getProvidedInterface( String interfaceName ) : 
		execution( public Object Manager.getProvidedInterface(String))
		&& args( interfaceName );
	
	Object around( String interfaceName ) : getProvidedInterface( interfaceName ){
		return variant.getProvidedInterface( interfaceName );
	}
	
	pointcut getProvidedInterfaces( ) : execution( public String[] Manager.getProvidedInterfaces() );
	
	String[] around( ) : getProvidedInterfaces(){
		return variant.getProvidedInterfaces( );
	}
	
}
