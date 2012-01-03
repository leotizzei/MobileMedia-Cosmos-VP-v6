package br.unicamp.ic.sed.mobilemedia.filesystemmgr.impl;

import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.dt.ImageData;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidArrayFormatException;
import br.unicamp.ic.sed.mobilemedia.filesystemmgr.spec.excep.InvalidImageDataException;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public class MusicUtil extends ImageUtil {

	public byte[] getBytesFromMediaInfo(IImageData ii) throws InvalidImageDataException {
		
		try {
			byte[] mediadata = super.getBytesFromImageInfo(ii);
			
				String byteString = new String(mediadata);
				byteString = byteString.concat(DELIMITER);

				byteString = byteString.concat( ii.getType() );
					
				return byteString.getBytes();

		} catch (Exception e) {
			throw new InvalidImageDataException("The provided data are not valid");
		}
		
	}

	public IImageData getMusicInfoFromBytes(byte[] bytes)
			throws InvalidArrayFormatException {
		ImageData mediadata =  (ImageData) super.getImageInfoFromBytes(bytes);
		String iiString = new String(bytes);
		
		
		int startIndex = getEndIndex() + 1;
		if (getEndIndex()==iiString.length()){
			return mediadata;	
		}
		setEndIndex(iiString.indexOf(DELIMITER, startIndex));
		
		if (getEndIndex() == -1)
			setEndIndex(iiString.length());
		String mediaType = iiString.substring(startIndex, getEndIndex());
		mediadata.setType(mediaType);
		return mediadata;
	}
}