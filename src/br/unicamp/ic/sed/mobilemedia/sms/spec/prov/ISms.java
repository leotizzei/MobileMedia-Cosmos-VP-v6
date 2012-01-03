 

package br.unicamp.ic.sed.mobilemedia.sms.spec.prov;

import br.unicamp.ic.sed.mobilemedia.sms.impl.ControllerInterface;

public interface ISms extends ControllerInterface{
	public void addMediaArrayOfBytes(String photoname, byte[] data1, String albumname);
	public byte[] loadImageBytesFromRMS(String recordName, String imageName,int recordId);
	public void setSelectedImageName( String imageName );
}
