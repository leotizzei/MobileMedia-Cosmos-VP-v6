package br.unicamp.ic.sed.mobilemedia.copyphoto.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.copyphoto.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov.ICopyPhoto;
import br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov.IManager;

public abstract aspect CopyPhoto_Photo {
	
	private String lastImageName = null;
		
	public static final Command copyCommand = new Command("Copy", Command.ITEM, 1);

	abstract public pointcut showImage(String name);
	
	after(String name):showImage(name){
		System.out.println("COPY PHOTO: " + name );
		lastImageName = name;
	}
	
	
	abstract public pointcut postCommand(Command c);
	
		
	boolean around(Command c):postCommand(c){
		if( c != null ) {
			String label = c.getLabel();
			if( ( label.equals("Copy"))) {
				
				IManager mgr = ComponentFactory.createInstance();
				
				ICopyPhoto copyPhoto = (ICopyPhoto) mgr.getProvidedInterface("ICopyPhoto");
				
				return copyPhoto.postCommand( c , lastImageName);
			
			}
			else{
				return proceed(c);
			}
			
		}
		
		return false;
	}
	
	abstract public pointcut addCopyCommand(javax.microedition.lcdui.Displayable photoViewScreen);

	
	after(javax.microedition.lcdui.Displayable photoViewScreen):
		addCopyCommand(photoViewScreen){
		
		photoViewScreen.addCommand( copyCommand );
		
		System.out.println( "-=-=-=-=-=-: " + photoViewScreen + " : " + copyCommand );
	}
	
	//=========== PhotoViewController
	public pointcut handleCommand():
		call(void br.unicamp.ic.sed.mobilemedia.copyphoto.impl.PhotoViewController.savePhoto())
		&& withincode(boolean br.unicamp.ic.sed.mobilemedia.copyphoto.impl.PhotoViewController.handleCommand(Command));
	
	public pointcut handleException() : execution(boolean br.unicamp.ic.sed.mobilemedia.copyphoto.impl.PhotoViewController.handleCommand(Command)); 
	
}
