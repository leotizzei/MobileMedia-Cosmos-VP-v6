/**
 * Institute of Computing - University of Campinas - Brazil
 * Software Engineering and Dependability Group
 * 
 * This class was added in MobileMedia-Cosmos-AO-v2
 * Sorting aspect. 
 * 
 */

package br.unicamp.ic.sed.mobilemedia.sorting.aspects;

import br.unicamp.ic.sed.mobilemedia.sorting.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.sorting.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.sorting.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;


public abstract aspect FilesystemMgrSorting {

	private static final String DELIMITER = "*";
	



	public abstract pointcut getBytesFromImageInfo(IImageData ii);

	byte[] around(IImageData ii):getBytesFromImageInfo(ii){

		byte[] imageByte = proceed(ii);
		String byteString = new String( imageByte );


		byteString = byteString.concat( DELIMITER );
		byteString = byteString.concat(""+ii.getNumberOfViews());
		
//		System.out.println("[FilesystemMgrSorting.getBytesFromImageInfo] byteString="+byteString);
		return byteString.getBytes();
	}

	/************************************************************************/

	public abstract pointcut createImageData(int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex  );

	after( int foreignRecordId, String parentAlbumName,String imageLabel , byte[] bytes , int endIndex )
	returning(IImageData imageData) :
		createImageData( foreignRecordId, parentAlbumName,imageLabel , bytes , endIndex ){

		int start = endIndex +1;

		String iiString = new String(bytes);

		endIndex = iiString.indexOf(DELIMITER, start);

		if (endIndex == -1)
			endIndex = iiString.length();

		System.out.println("[FilesystemMgrSorting:createImageData]iiString="+iiString+", start="+start+", endIndex="+endIndex);
		int numberOfViews =  getNumberOfViews(iiString,start, endIndex);
		System.out.println("[FilesystemMgrSorting:createImageData] numberOfViews="+numberOfViews);
		imageData.setNumberOfViews(numberOfViews);
		System.out.println("[FilesystemMgrSorting] returning...");
	}

	//created to create a joinpoint.
	protected int getNumberOfViews( String iiString , int start , int endIndex ){
		System.out.println("[FilesystemMgrSorting.getNumberOfViews("+iiString+","+start+","+endIndex+")]");
		String str = null; 
		if( start <= endIndex ){
			str = iiString.substring(start, endIndex);
			System.out.println("[FilesystemMgrSorting.getNumberOfViews()] str = "+str);
			if( str != null ){
				try{
					int result = Integer.parseInt( str );
					return result;
				}catch(NumberFormatException e){
				System.out.println("[FilesystemMgrSorting.getNumberOfViews()] not a number");
					e.printStackTrace();
					return 0;
				}
			}
			else{
				return 0;
			}
		}
		else{
			return 0;
		}
	}

	/************************************************************************/

	public abstract pointcut updateCounter(String albumName, String imageName);

	after(String albumName, String imageName):updateCounter(albumName, imageName){


		//search an specific image
		IImageData imageData = this.getImage( albumName , imageName ); 

		if( imageData != null){
//			System.out.println("[updateCounter]Current view counter:"+imageData.getNumberOfViews()+" of image "+imageData.getImageLabel());

			this.increaseNumberOfViews(imageData);

		}
	}



	/**
	 * 
	 * @param images an array of ImageData
	 * @param imageName the name of the searched ImageData
	 * @return 
	 */
	protected IImageData getImage(String albumName , String imageName){
//		System.out.println("FilesystemMGrSorting.getImage("+albumName+","+imageName+")");
		IImageData[] images;

		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");
		
		
		
		images = filesystem.getImages( albumName );

		

		if( ( images != null ) && ( imageName != null ) ){
			boolean found = false;
			int i = 0;
			while( (!found) && ( i < images.length) ){
				String label = images[i].getImageLabel();
				if( label.equals( imageName ))
					found = true;
				else
					i++;
			}
			if( found )
				return images[i];
		}

		System.err.println("Image was not found!");
		return null;

	}

	//created to create a joinpoint.
	protected void increaseNumberOfViews( IImageData imageData ){
		imageData.increaseNumberOfViews();

		IManager manager = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem) manager.getRequiredInterface("IFilesystem");

		boolean result = filesystem.updateImageInfo( imageData, imageData );

		if( result )
			System.out.println("ImageData which label is "+imageData.getImageLabel()+" was updated successfully!");
		else
			System.out.println("ImageData was NOT updated");

//		System.out.println("[aspectj]Updated view counter:"+imageData.getNumberOfViews()+" of image "+imageData.getImageLabel());
	}


}
