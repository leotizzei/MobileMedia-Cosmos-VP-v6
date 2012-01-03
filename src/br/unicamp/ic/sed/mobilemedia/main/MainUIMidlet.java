package br.unicamp.ic.sed.mobilemedia.main;

import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IMobilePhone;
import br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IAlbum;


//Following are pre-processor statements to include the required
//classes for device specific features. They must be commented out
//if they aren't used, otherwise it will throw exceptions trying to
//load classes that aren't available for a given platform.


/* 
 * 
 *
 * This is the main Midlet class for the core J2ME application
 * It contains all the basic functionality that should be executable
 * in any standard J2ME device that supports MIDP 1.0 or higher. 
 * Any additional J2ME features for this application that are dependent
 * upon a particular device (ie. optional or proprietary library) are
 * de-coupled from the core application so they can be conditionally included
 * depending on the target platform 
 * 
 * This Application provides a basic Photo Album interface that allows a user to view
 * images on their mobile device. 
 * */
public class MainUIMidlet extends MIDlet implements IMobileResources {

	//components
	br.unicamp.ic.sed.mobilemedia.album.spec.prov.IManager album;
	br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.prov.IManager filesystem;
	br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IManager photo;
	br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.prov.IManager mobilePhone;
	br.unicamp.ic.sed.mobilemedia.exceptionhandler.spec.prov.IManager exceptionHandler;
	
	
	

	//connectors
	br.unicamp.ic.sed.mobilemedia.album_mobilephonemgr.impl.IManager album_mobilePhone;
	br.unicamp.ic.sed.mobilemedia.filesystemmgr_mobilephonemgr.impl.IManager filesystem_mobilePhone;
	br.unicamp.ic.sed.mobilemedia.mobilephonemgr_photo.impl.IManager mobilephonemgr_photo;
	br.unicamp.ic.sed.mobilemedia.mainuimidlet_album.impl.IManager mainMidlet_album;
	br.unicamp.ic.sed.mobilemedia.mainuimidlet_photo.impl.IManager mainMidlet_photo;
	br.unicamp.ic.sed.mobilemedia.mainuimidlet_mobilephonemgr.impl.IManager mainMidlet_mobilePhone;
	br.unicamp.ic.sed.mobilemedia.filesystemmgr_album.impl.IManager filesystemmgr_album;
	br.unicamp.ic.sed.mobilemedia.filesystemmgr_photo.impl.IManager filesystemmgr_photo;
	br.unicamp.ic.sed.mobilemedia.photo_album.impl.IManager photo_album;
	br.unicamp.ic.sed.mobilemedia.mainuimidlet_exceptionhandler.impl.IManager mainMidlet_exceptionHandler;
	
	br.unicamp.ic.sed.mobilemedia.sorting.spec.prov.IManager sorting;
	br.unicamp.ic.sed.mobilemedia.favourites.spec.prov.IManager favourites;
	br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl.IManager filesystem_connector_variant;
	br.unicamp.ic.sed.mobilemedia.copyphoto.spec.prov.IManager copyphoto; 
	
	
	br.unicamp.ic.sed.mobilemedia.sms.spec.prov.IManager sms;
	br.unicamp.ic.sed.mobilemedia.sms_album.IManager sms_album;
	br.unicamp.ic.sed.mobilemedia.sms_photo.IManager sms_photo;
	
	br.unicamp.ic.sed.mobilemedia.music.spec.prov.IManager music;
	br.unicamp.ic.sed.mobilemedia.mainuimidlet_connector_VP.impl.IManager mainuimidlet_connector_variant;
	
	br.unicamp.ic.sed.mobilemedia.excep_filesystem.impl.IManager eh_album;
	br.unicamp.ic.sed.mobilemedia.excep_sms_VP.impl.IManager eh_connector_variant;
	
	public MainUIMidlet() {
		//do nothing
	}

	/**
	 * Start the MIDlet by creating new model and controller classes, and
	 * initialize them as necessary
	 */
	public void startApp() throws MIDletStateChangeException {

		Configurator.configureComponentVP();

		
		
		// create all imanagers
		filesystem = br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl.ComponentFactory.createInstance();

		album_mobilePhone = br.unicamp.ic.sed.mobilemedia.album_mobilephonemgr.impl.ComponentFactory.createInstance();

		mobilePhone = br.unicamp.ic.sed.mobilemedia.mobilephonemgr.impl.ComponentFactory.createInstance();

		album = br.unicamp.ic.sed.mobilemedia.album.impl.ComponentFactory.createInstance();

		exceptionHandler = br.unicamp.ic.sed.mobilemedia.exceptionhandler.impl.ComponentFactory.createInstance();
		
		filesystem_mobilePhone = br.unicamp.ic.sed.mobilemedia.filesystemmgr_mobilephonemgr.impl.ComponentFactory.createInstance();
		
		mainMidlet_album = br.unicamp.ic.sed.mobilemedia.mainuimidlet_album.impl.ComponentFactory.createInstance();
		
		photo = br.unicamp.ic.sed.mobilemedia.photo.impl.ComponentFactory.createInstance();
		
		mobilephonemgr_photo = br.unicamp.ic.sed.mobilemedia.mobilephonemgr_photo.impl.ComponentFactory.createInstance();
		
		mainMidlet_photo = br.unicamp.ic.sed.mobilemedia.mainuimidlet_photo.impl.ComponentFactory.createInstance();
		
		mainMidlet_mobilePhone = br.unicamp.ic.sed.mobilemedia.mainuimidlet_mobilephonemgr.impl.ComponentFactory.createInstance();
		
		filesystemmgr_album = br.unicamp.ic.sed.mobilemedia.filesystemmgr_album.impl.ComponentFactory.createInstance();
		
		filesystemmgr_photo = br.unicamp.ic.sed.mobilemedia.filesystemmgr_photo.impl.ComponentFactory.createInstance();
		
		photo_album = br.unicamp.ic.sed.mobilemedia.photo_album.impl.ComponentFactory.createInstance();
		
		mainMidlet_exceptionHandler = br.unicamp.ic.sed.mobilemedia.mainuimidlet_exceptionhandler.impl.ComponentFactory.createInstance();
		
		sorting = br.unicamp.ic.sed.mobilemedia.sorting.impl.ComponentFactory.createInstance();
		favourites = br.unicamp.ic.sed.mobilemedia.favourites.impl.ComponentFactory.createInstance();
		filesystem_connector_variant = br.unicamp.ic.sed.mobilemedia.filesystemmgr_connector_VP.impl.ComponentFactory.createInstance();
		copyphoto = br.unicamp.ic.sed.mobilemedia.copyphoto.impl.ComponentFactory.createInstance();
		
		sms = br.unicamp.ic.sed.mobilemedia.sms.impl.ComponentFactory.createInstance();
		sms_album = br.unicamp.ic.sed.mobilemedia.sms_album.ComponentFactory.createInstance();
		sms_photo = br.unicamp.ic.sed.mobilemedia.sms_photo.ComponentFactory.createInstance();
		
		music = br.unicamp.ic.sed.mobilemedia.music.impl.ComponentFactory.createInstance();
		mainuimidlet_connector_variant = br.unicamp.ic.sed.mobilemedia.mainuimidlet_connector_VP.impl.ComponentFactory.createInstance();
		
		eh_album = br.unicamp.ic.sed.mobilemedia.excep_filesystem.impl.ComponentFactory.createInstance();
		eh_connector_variant = br.unicamp.ic.sed.mobilemedia.excep_sms_VP.impl.ComponentFactory.createInstance();
		
		/*********************************************************************************************/
		//setting required interfaces
	
		eh_album.setRequiredInterface("IExceptionHandler", exceptionHandler.getProvidedInterface("IExceptionHandler"));
		eh_connector_variant.setRequiredInterface("IExceptionHandler", exceptionHandler.getProvidedInterface("IExceptionHandler"));
		
		//component mobilePhoneMgr
		mobilePhone.setRequiredInterface("IFilesystem", filesystem_mobilePhone.getProvidedInterface("IFilesystem"));
	
		mobilePhone.setRequiredInterface("IMobileResources", mainMidlet_mobilePhone.getProvidedInterface("IMobileResources"));
	
		br.unicamp.ic.sed.mobilemedia.mobilephonemgr.spec.req.IAlbum ialbum = (IAlbum) album_mobilePhone.getProvidedInterface("IAlbum");
	
		mobilePhone.setRequiredInterface("IAlbum", ialbum );
	
		
		//component album
		
		album.setRequiredInterface("IMobilePhone", album_mobilePhone.getProvidedInterface("IMobilePhone"));

		album.setRequiredInterface("IMobileResources", mainMidlet_album.getProvidedInterface("IMobileResources") );

		album.setRequiredInterface("IPhoto", photo_album.getProvidedInterface("IPhoto"));

		album.setRequiredInterface("IFilesystem",filesystemmgr_album.getProvidedInterface("IFilesystem"));

		//album.setRequiredInterface("IExceptionHandler", album_exceptionhandler.getProvidedInterface("IExceptionHandler"));
		
		
		// component photo
		photo.setRequiredInterface("IMobilePhone", mobilephonemgr_photo.getProvidedInterface("IMobilePhone"));

		photo.setRequiredInterface("IMobileResources", mainMidlet_photo.getProvidedInterface("IMobileResources") );

		photo.setRequiredInterface("IFilesystem", filesystemmgr_photo.getProvidedInterface("IFilesystem"));

		mainMidlet_photo.setRequiredInterface("IMobileResources", this );

		mainMidlet_mobilePhone.setRequiredInterface("IMobileResources", this);

		filesystemmgr_album.setRequiredInterface("IFilesystem", filesystem.getProvidedInterface("IFilesystem"));

		filesystemmgr_photo.setRequiredInterface("IFilesystem", filesystem.getProvidedInterface("IFilesystem"));

		photo_album.setRequiredInterface("IPhoto", photo.getProvidedInterface("IPhoto"));

		filesystem_mobilePhone.setRequiredInterface("IFilesystem", filesystem.getProvidedInterface("IFilesystem"));
	
		album_mobilePhone.setRequiredInterface("IAlbum", album.getProvidedInterface("IAlbum"));
		
		album_mobilePhone.setRequiredInterface("IMobilePhone", mobilePhone.getProvidedInterface("IMobilePhone"));
		
		mobilephonemgr_photo.setRequiredInterface("IPhoto", photo.getProvidedInterface("IPhoto"));
		
		mobilephonemgr_photo.setRequiredInterface("IMobilePhone", mobilePhone.getProvidedInterface("IMobilePhone"));

		mainMidlet_album.setRequiredInterface("IMobileResources", this );

		mainMidlet_exceptionHandler.setRequiredInterface("IMobileResources",  this );
		mainuimidlet_connector_variant.setRequiredInterface("IMobileResources",  this );
		
		exceptionHandler.setRequiredInterface("IMobileResources", mainMidlet_exceptionHandler.getProvidedInterface("IMobileResources"));
		
		sorting.setRequiredInterface("IFilesystem", filesystem_connector_variant.getProvidedInterface("IFilesystemSorting"));
		favourites.setRequiredInterface("IFilesystem", filesystem_connector_variant.getProvidedInterface("IFilesystemFavourites"));
		
		filesystem_connector_variant.setRequiredInterface("IFilesystem", filesystem.getProvidedInterface("IFilesystem"));
		
		copyphoto.setRequiredInterface("IFilesystem", filesystem_connector_variant.getProvidedInterface("IFilesystemCopyPhoto"));
		copyphoto.setRequiredInterface("IMobileResources",mainuimidlet_connector_variant.getProvidedInterface("IMobileResourcesCopyPhoto"));
		
		sms_album.setRequiredInterface("IAlbum", album.getProvidedInterface("IAlbum"));
		sms_photo.setRequiredInterface("IPhoto", photo.getProvidedInterface("IPhoto"));
		
		sms.setRequiredInterface("IAlbum" , sms_album.getProvidedInterface("IAlbum"));
		sms.setRequiredInterface("IFilesystem" , filesystem_connector_variant.getProvidedInterface("IFilesystemSms"));
		sms.setRequiredInterface("IPhoto", sms_photo.getProvidedInterface("IPhoto"));
		sms.setRequiredInterface("IMobileResources" , mainuimidlet_connector_variant.getProvidedInterface("IMobileResourcesSms"));
		
		sms.getProvidedInterface("ISms");
		
		music.setRequiredInterface( "IMobileResources" , mainuimidlet_connector_variant.getProvidedInterface("IMobileResourcesMusic") );
		music.setRequiredInterface( "IFilesystemMusic" ,  filesystem_connector_variant.getProvidedInterface( "IFilesystemMusic"  ));
				
		mainuimidlet_connector_variant.setRequiredInterface("IMobileResources", this );
		
		IMobilePhone mobPhone = (IMobilePhone) mobilePhone.getProvidedInterface("IMobilePhone");
		
		mobPhone.startUp();
	}

	/**
	 * Pause the MIDlet
	 * This method does nothing at the moment.
	 */
	public void pauseApp() {
		//do nothing
	}

	/**
	 * Destroy the MIDlet
	 */
	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public MIDlet getMainMIDlet() {
		return this;
	}
	
}