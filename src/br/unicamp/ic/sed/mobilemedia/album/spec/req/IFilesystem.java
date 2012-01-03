package br.unicamp.ic.sed.mobilemedia.album.spec.req;


public interface IFilesystem{
	
		
	public String[] getAlbumNames (  ) ; 
	
	public void resetImageData (  ) ; 
	
	public void createNewPhotoAlbum ( String albumName ); 
	
	public void deletePhotoAlbum ( String albumName ) ;
		
}