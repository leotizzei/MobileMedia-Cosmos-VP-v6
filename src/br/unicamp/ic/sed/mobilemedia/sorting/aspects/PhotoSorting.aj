/**
 * Institute of Computing - University of Campinas - Brazil
 * Software Engineering and Dependability Group
 * 
 * Added in MobileMedia-Cosmos-AO-v2
 * Sorting aspect. 
 * 
 */
package br.unicamp.ic.sed.mobilemedia.sorting.aspects;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;


public abstract aspect PhotoSorting {
	//public abstract pointcut initPhotoListScreen(String[] imageNames, PhotoListScreen screen);

	public static final Command sortCommand = new Command("Sort by Views", Command.ITEM, 1);

	boolean sort = false;
	
	private boolean fakeSelect = false;
	
	/*before(String[] imageNames, PhotoListScreen screen) : initPhotoListScreen(imageNames, screen) {

		System.out.println("Adding 'Sort by Views' command");
		screen.addCommand(sortCommand);
	}*/

	public abstract pointcut initMenu(javax.microedition.lcdui.List photoListScreen);

	void around(javax.microedition.lcdui.List photoListScreen):
		initMenu( photoListScreen ){
		//System.out.println("[PhotoSorting] Adding 'Sort by Views' command to PhotoListScreen "+photoListScreen);
		photoListScreen.addCommand(sortCommand);
		proceed( photoListScreen );
	}

	public abstract pointcut postCommand(Command c);

	

	boolean around(Command c):postCommand(c){
		if(c != null){
			String label = c.getLabel();
			if( label.equals("Sort by Views")){
				sort = true;
				fakeSelect = true;
				return true;
			}
		}
		return false;
	}

	abstract public pointcut setCurrentStoreName(String storeName);
	
	boolean around(String storeName):setCurrentStoreName(storeName){
		//System.out.println("=====fakeSelect="+fakeSelect);
		if( fakeSelect == false ){
			
			proceed(storeName);
			return true;
		}
		else{
			fakeSelect = false;
			return false;
		}
	}
	
	/**************************************************************************/
	public abstract pointcut getImages(String recordName);

	after( String recordName ) returning(IImageData[] images):getImages( recordName ){
		if(sort){
			this.bubbleSort(images);
		}
		sort = false;
	}

	/**
	 * Sorts an int array using basic bubble sort
	 * 
	 * @param numbers the int array to sort
	 */
	private void bubbleSort(IImageData[] images) {
		System.out.print("Sorting by BubbleSort...");		
		for (int end = images.length; end > 1; end --) {
			for (int current = 0; current < end - 1; current ++) {
				if (images[current].getNumberOfViews() > images[current+1].getNumberOfViews()) {
					exchange(images, current, current+1);
				}
			}
		}
		//System.out.println("done.");
	}

	/**
	 * @param images
	 * @param pos1
	 * @param pos2
	 */
	private void exchange(IImageData[] images, int pos1, int pos2) {
		IImageData tmp = images[pos1];
		images[pos1] = images[pos2];
		images[pos2] = tmp;
	}
}

