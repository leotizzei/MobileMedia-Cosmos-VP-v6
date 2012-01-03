package br.unicamp.ic.sed.mobilemedia.favourites.aspects;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;


public abstract aspect FilesystemMgrFavourites {

	private static final String DELIMITER = "*";


	public abstract pointcut getBytesFromImageInfo(IImageData ii);

	byte[] around(IImageData ii):getBytesFromImageInfo(ii){

		byte[] bytes = proceed( ii );
		String byteString = new String( bytes );

		byteString = byteString.concat( DELIMITER );
		if(ii.isFavourite()){
			//System.out.println("[FilesystemMgrFavourites.getBytesFromImageInfo()] byteString antes ="+byteString);
			byteString = byteString.concat("true");
			//System.out.println("[FilesystemMgrFavourites.getBytesFromImageInfo()] byteString depois="+byteString);
		}
		else{
			//System.out.println("[FilesystemMgrFavourites.getBytesFromImageInfo()] byteString antes ="+byteString);
			byteString = byteString.concat("false");
			//System.out.println("[FilesystemMgrFavourites.getBytesFromImageInfo()] byteString depois="+byteString);
		}

		//System.out.println("<favourites>: " + byteString );
		return byteString.getBytes();
	}

	/***************************************************************************/

	public abstract pointcut createImageData(int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex  );

	IImageData around( int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex ):
		createImageData( foreignRecordId, parentAlbumName,imageLabel , bytes , endIndex ){

		//System.out.println("[FilesystemMgrFavourite] begin");
		IImageData imageData = proceed( foreignRecordId, parentAlbumName, imageLabel, bytes , endIndex );

		String iiString = new String(bytes);

		int start = 0;
		//System.out.println("[FilesystemMgrFavourite] iiString="+iiString);
		//System.out.println("[FilesystemMgrFavourite]before while start="+start+" endIndex="+endIndex);
		while( endIndex != -1 ){
			//System.out.println("[FilesystemMgrFavourite]start="+start+" endIndex="+endIndex);
			start = endIndex +1;
			endIndex = iiString.indexOf(DELIMITER, start);
		}
		endIndex = iiString.length();
//		System.out.println("[FilesystemMgrFavourite]after while start="+start+" endIndex="+endIndex);
//		System.out.println("[FilesystemMgrFavourite] iiString="+iiString);

		if( start <= endIndex){
			String aux = iiString.substring(start, endIndex);
			boolean favourite = aux.equalsIgnoreCase("true");
//			System.out.println("[FilesystemMgrFavourite] favourite="+favourite+" imageData="+imageData);
			imageData.setFavourite( favourite );
//			System.out.println("[FilesystemMgrFavourite] isFavourite="+favourite);
		}else{
//			System.out.println("[FilesystemMgrFavourite] isFavourite=false");
			imageData.setFavourite( false );
		}


		return imageData;
	}
}
