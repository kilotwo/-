package com.example.pingping_game2.musicTools;

public class MusicPlayThread extends Thread{

	public MusicPlayThread() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		musicPlayer.play();
	}

}
