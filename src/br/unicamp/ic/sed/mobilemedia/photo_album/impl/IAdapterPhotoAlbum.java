package br.unicamp.ic.sed.mobilemedia.photo_album.impl;

import javax.microedition.lcdui.Command;

import br.unicamp.ic.sed.mobilemedia.album.spec.req.IPhoto;

public class IAdapterPhotoAlbum implements IPhoto {

	public boolean postCommand(Command c) {
		System.out.println("[IAdapterPhotoAlbum.postCommand("+c.getLabel()+")");
		IManager manager = ComponentFactory.createInstance();
		System.out.println(this.getClass().getName()+" manager="+manager);
		br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto photo = (br.unicamp.ic.sed.mobilemedia.photo.spec.prov.IPhoto)manager.getRequiredInterface("IPhoto");
		System.out.println(this.getClass().getName()+" photo="+photo);
		return photo.postCommand(c);
	}

}
