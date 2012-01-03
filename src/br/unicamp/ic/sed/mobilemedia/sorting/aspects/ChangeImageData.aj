package br.unicamp.ic.sed.mobilemedia.sorting.aspects;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect ChangeImageData {

	

	//private static final String INFO_LABEL = "mpi-"; // "mpi- all album info

	// Added in the MobileMedia-Cosmos-AO-v2 
	private int IImageData.numberOfViews = 0;

	/**
	 * Added in the MobileMedia-Cosmos-AO-v2 
	 */
	public void IImageData.increaseNumberOfViews(){
		
		numberOfViews++; 
	}

	/**
	 * Added in the MobileMedia-Cosmos-AO-v2 
	 * @return the numberOfViews
	 */
	public int IImageData.getNumberOfViews() {
		return numberOfViews;
	}

	/**
	 * Added in the MobileMedia-Cosmos-AO-v2
	 * @param views
	 */
	public void IImageData.setNumberOfViews(int views) {
		numberOfViews = views;
	}
}