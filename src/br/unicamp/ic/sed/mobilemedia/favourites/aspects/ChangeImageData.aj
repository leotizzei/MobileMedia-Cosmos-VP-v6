/**
 * Institute of Computing - University of Campinas - Brazil
 * Software Engineering and Dependability Group
 * 
 * This class was added in MobileMedia-Cosmos-AO-v3
 * Favourites aspect. 
 * 
 * @author Marcelo.
 */

package br.unicamp.ic.sed.mobilemedia.favourites.aspects;

import br.unicamp.ic.sed.mobilemedia.main.spec.dt.IImageData;

public aspect ChangeImageData {
	
	private boolean IImageData.favourite = false;
	
	public void IImageData.setFavourite( boolean favour ){
		favourite = favour;
	}
	
	public boolean IImageData.isFavourite(){
		return favourite;
	}
	
	public void IImageData.toggleFavourites(){
		favourite = !favourite;
	}
}
