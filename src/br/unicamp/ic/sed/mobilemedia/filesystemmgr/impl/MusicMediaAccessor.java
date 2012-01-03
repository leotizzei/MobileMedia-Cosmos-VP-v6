package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImageNotFoundException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.ImagePathNotValidException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidArrayFormatException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageFormatException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.PersistenceMechanismException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public class MusicMediaAccessor extends ImageAccessor {
 
	private MusicUtil converter = new MusicUtil();
	
	public MusicMediaAccessor() {
		super("mmp-","mmpi-","My Music Album");
	}
	
	protected  byte[] getMediaArrayOfByte(String path)	throws ImagePathNotValidException, InvalidImageFormatException {
		byte[] data1 = converter.readImageAsByteArray(path);
		return data1;
	}
	
	protected byte[] getByteFromMediaInfo(IImageData ii) throws InvalidImageDataException {
			return converter.getBytesFromMediaInfo(ii);
	}
	
	protected IImageData getMediaFromBytes(byte[] data) throws InvalidArrayFormatException {
		IImageData iiObject = converter.getMusicInfoFromBytes(data);
		return iiObject;
	}

	public void resetImageRecordStore() throws InvalidImageDataException,PersistenceMechanismException {
		String storeName = null;
		String infoStoreName = null;

		// remove any existing album stores...
		if (albumNames != null) {
			for (int i = 0; i < albumNames.length; i++) {
				try {
					// Delete all existing stores containing Image objects as
					// well as the associated ImageInfo objects
					// Add the prefixes labels to the info store

					storeName = album_label + albumNames[i];
					infoStoreName = info_label + albumNames[i];
					
					RecordStore.deleteRecordStore(storeName);
					RecordStore.deleteRecordStore(infoStoreName);

				} catch (RecordStoreException e) {
					System.out.println("No record store named " + storeName
							+ " to delete.");
					System.out.println("...or...No record store named "
							+ infoStoreName + " to delete.");
					System.out.println("Ignoring Exception: " + e);
					// ignore any errors...
				}
			}
		} else {
			// Do nothing for now
			System.out
					.println("ImageAccessor::resetImageRecordStore: albumNames array was null. Nothing to delete.");
		}

		// Now, create a new default album for testing
		IImageData music;

		addImageData("Applause", "/images/applause.wav", default_album_name);
		addImageData("Baby", "/images/baby.wav", default_album_name);
		addImageData("Bong", "/images/bong.wav", default_album_name);
		addImageData("Frogs", "/images/frogs.mp3", default_album_name);
		addImageData("Jump", "/images/jump.wav", default_album_name);
		addImageData("Printer", "/images/printer.wav", default_album_name);
		addImageData("Tango", "/images/cabeza.mid", default_album_name);
		
		loadImageDataFromRMS(default_album_name);

		try {
			music = this.getImageInfo("Applause");
			music.setType("audio/x-wav");
			this.updateImageInfo( music, music );

			music = this.getImageInfo("Baby");
			music.setType("audio/x-wav");
			this.updateImageInfo( music , music );

			music = this.getImageInfo("Bong");
			music.setType("audio/x-wav");
			this.updateImageInfo( music , music );

			music = this.getImageInfo("Frogs");
			music.setType("audio/x-wav");
			this.updateImageInfo( music , music );

			music = this.getImageInfo("Jump");
			music.setType("audio/x-wav");
			this.updateImageInfo( music , music );

			music = this.getImageInfo("Printer");
			music.setType("audio/x-wav");
			this.updateImageInfo( music , music );
			
			music = this.getImageInfo("Tango");
			music.setType("audio/x-wav");
			this.updateImageInfo( music , music );
		} catch (ImageNotFoundException e) {
			e.printStackTrace();
		}

	}

}
