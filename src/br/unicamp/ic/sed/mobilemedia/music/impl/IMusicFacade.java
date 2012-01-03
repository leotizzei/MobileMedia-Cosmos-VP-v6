package br.unicamp.ic.sed.mobilemedia.music.impl;

import br.unicamp.ic.sed.mobilemedia.music.spec.prov.IMusic;

class IMusicFacade implements IMusic{

	private MusicController mainPhotocontroller = new MusicController();

	public void playMusic(String albumName, String musicName) {
		this.mainPhotocontroller.playMusic(albumName, musicName);
	}

}