package br.unicamp.ic.sed.mobilemedia.music.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.music.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.music.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.music.spec.prov.IMusic;

public abstract aspect Photo {

	
	
	public abstract pointcut handleCommand( Command c , String recordName , String musicName );
	
	boolean around( Command c , String recordName , String musicName ) : handleCommand( c , recordName , musicName ){	
		
		IManager manager = ComponentFactory.createInstance();
		IMusic music = (IMusic) manager.getProvidedInterface("IMusic");
		
		if( c.getLabel().equals( "Play" )){
			music.playMusic( recordName , musicName );
			return true;
		}
		
		return false;
	}
	
	public abstract pointcut addCommand( String command , int type , int posi );
	
	Command around( String command , int type , int posi ) : addCommand( command , type , posi ){
		if( command.equals("View") )
			command = "Play";
		
		return new Command( command , type , posi );
		
	}
}