package br.unicamp.ic.sed.mobilemedia.sms.impl;

import javax.microedition.rms.RecordStore;

import br.unicamp.ic.sed.mobilemedia.sms.spec.dt.ImageData;
import br.unicamp.ic.sed.mobilemedia.sms.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.sms.spec.req.IFilesystem;

class SmsImageAccessor {
	
	// Record Stores
	private RecordStore imageRS = null;
	private RecordStore imageInfoRS = null;

	private static final String ALBUM_LABEL = "mpa-"; // "mpa- all album names
	private static final String INFO_LABEL = "mpi-"; // "mpi- all album info
	
	private IManager manager = ComponentFactory.createInstance();
	
	protected byte[] loadImageBytesFromRMS(String recordName, String imageName,int recordId){

		byte[] imageData = null;
		
		RecordStore albumStore = RecordStore.openRecordStore(recordName,false);
		imageData = albumStore.getRecord(recordId);
		albumStore.closeRecordStore();
		
		return imageData;
	}
	
	public void addMediaArrayOfBytes(String photoname, byte[] data1, String albumname){	
		imageRS = RecordStore.openRecordStore(ALBUM_LABEL + albumname, false);
		imageInfoRS = RecordStore.openRecordStore(INFO_LABEL + albumname, false);
		
		int rid; // new record ID for Image (bytes)
		int rid2; // new record ID for ImageData (metadata)
		rid = imageRS.addRecord(data1, 0, data1.length);
		ImageData ii = new ImageData(rid, ALBUM_LABEL + albumname, photoname);
		rid2 = imageInfoRS.getNextRecordID();
		ii.setRecordId(rid2);
		
		
		IFilesystem filesystem = (IFilesystem)manager.getRequiredInterface("IFilesystem");
		data1 = filesystem.getBytesFromImageInfo(ii);
		imageInfoRS.addRecord(data1, 0, data1.length);
				
		imageRS.closeRecordStore();
		imageInfoRS.closeRecordStore();
	}
}
