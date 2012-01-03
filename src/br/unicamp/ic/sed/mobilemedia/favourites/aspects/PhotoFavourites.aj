package br.unicamp.ic.sed.mobilemedia.favourites.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.favourites.impl.ComponentFactory;
import br.unicamp.ic.sed.mobilemedia.favourites.spec.prov.IManager;
import br.unicamp.ic.sed.mobilemedia.favourites.spec.req.IFilesystem;
import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public abstract aspect PhotoFavourites {

	//public abstract pointcut initPhotoListScreen(String[] imageNames, PhotoListScreen screen);

	public static final Command setFavouriteCommand = new Command("Set Favourite", Command.ITEM, 1);
	public static final Command viewFavouritesCommand = new Command("View Favourites", Command.ITEM, 1);
	private static boolean selectFavourites = false;
	
	

	public abstract pointcut initMenu(javax.microedition.lcdui.List photoListScreen);

	void around(javax.microedition.lcdui.List photoListScreen):
		initMenu( photoListScreen ){
		//System.out.println("[PhotoFavourites] Adding commands to PhotoListScreen "+photoListScreen);
		photoListScreen.addCommand(setFavouriteCommand);
		photoListScreen.addCommand(viewFavouritesCommand);
		proceed( photoListScreen );
	}

	//==========================
	/*Added in MobileMedia-Cosmos-AO-v4*/
	public abstract pointcut postCommand( Command c , String imageName );

	static boolean favourites = false;
	String currentRecordName = null;

	boolean around( Command c , String imageName ):postCommand( c , imageName ){
		System.out.println("[PhotoFavourites] command: " + c.getLabel() );

		if(c != null){
			String label = c.getLabel();
			//Handling 'View Favourite' command
			if( label.equals("View Favourites")){
				favourites = true;
				selectFavourites = true;
				System.out.println("[PhotoFavourites] favourites="+favourites);
				return true; //Have to update the command.

			}else if( label.equals("Set Favourite")){
				this.setFavourite( imageName );
				return false; //Doesn't have to update the command.
			}
		}
		return false;
	}

	/*Added in MobileMedia-Cosmos-AO-v4*/
	void setFavourite( String imageName ){
		//System.out.println("[PhotoFavourites.setFavourite()]");

		//Getting the component FilesystemMgr.
		IManager managerFilesystem = ComponentFactory.createInstance();
		IFilesystem filesystem = (IFilesystem)managerFilesystem.getRequiredInterface("IFilesystem");
		//System.out.println("[PhotoFavourites] filesystem="+filesystem);


		IImageData[] images = filesystem.getImages( currentRecordName );
		//System.out.println("[PhotoFavourites] currentRecordName="+currentRecordName);

		IImageData imageData = this.searchImage( images , imageName );
		//System.out.println("[PhotoFavourites] imageLabel="+imageData.getImageLabel()+" isFavourite="+imageData.isFavourite());
		imageData.toggleFavourites();
		//System.out.println("[PhotoFavourites] imageLabel="+imageData.getImageLabel()+" isFavourite="+imageData.isFavourite());
		filesystem.updateImageInfo( imageData , imageData );

	}

	/**
	 * Added in MobileMedia-Cosmos-AO-v4
	 * @param images an array of ImageData
	 * @param imageName the name of the searched ImageData
	 * @return 
	 */
	private IImageData searchImage(IImageData[] images, String imageName){
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

	/***************************************************************/
	/**
	 * Added in MobileMedia-Cosmos-AO-v4
	 */
	public abstract pointcut getImages(String recordName );

	IImageData[] around( String recordName ):getImages( recordName ){
		//System.out.println("[PhotoFavourites.getImages()] recordName="+recordName);
		IImageData[] images = proceed( recordName );
		//System.out.println("[PhotoFavourites.getImages()] images="+images);
		currentRecordName = recordName;
		//System.out.println("[PhotoFavourites.getImages()] favourites="+favourites+" currentRecordName="+currentRecordName);
		if( favourites ){
			images = this.selectFavourites( images );
		}
		favourites = false;
		return images;
	}





	/**
	 * Added in MobileMedia-Cosmos-AO-v4
	 * @param images
	 * @return
	 */
	private IImageData[] selectFavourites( IImageData[] images ){

		System.out.println("<favourites> selecting favourites");
		int j = 0;

		//loop through array and add favourites labels to list
		for (int i = 0; i < images.length; i++) {
			if (images[i] != null) {
				if( images[ i ].isFavourite() ){
					images[ j++ ] = images[i];
				}
			}
		}

		IImageData[] imagesFavourites = new IImageData[ j ];
		for( int i = 0 ; i<j ; i++ ){
			imagesFavourites[ i ] = images[ i ];
		}

		for( int i = 0 ; i < imagesFavourites.length ; i++ ){
			System.out.println("<SelectFavourites> "+ imagesFavourites[i].getImageLabel() +" "+ imagesFavourites[i].isFavourite() );
		}

		return imagesFavourites;
	}
	//=====================
	
	abstract public pointcut setCurrentStoreName(String storeName);

	/**
	 * This advice skips a method call to setCurrentStoreName in 
	 * case the user has selected "view favourites" 
	 */
	boolean around(String storeName):setCurrentStoreName(storeName){
		System.out.println("=====[favourite]selectFavourites="+selectFavourites+" storeName="+storeName);
		if( selectFavourites == false ){
			System.out.println("**********NOT FAVOURITES***********");
			proceed(storeName);
			return true;
		}
		else{
			selectFavourites = false;
			return false;
		}
	}
}
