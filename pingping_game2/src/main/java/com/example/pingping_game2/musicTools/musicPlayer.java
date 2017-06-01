package com.example.pingping_game2.musicTools;

import java.net.ContentHandler;

import com.example.pingping_game2.R;

import android.content.Context;
import android.media.MediaPlayer;

public class musicPlayer {

	private static Context context;
	private static MediaPlayer mediaPlayer;
	private static boolean flag=true;
	public static void init(Context c) {
		// TODO Auto-generated constructor stub
		context=c;
		mediaPlayer=MediaPlayer.create(context, R.raw.backmusic);
		mediaPlayer.setLooping(true);
	}

	private void stopIt(){
		mediaPlayer.pause();
		mediaPlayer.seekTo(0);
	}
	
	public static void play(){
		mediaPlayer.start();
	}
	
	public static boolean getflag(){
		return flag;
	}
	
	public static void setFlag(boolean Flag){
		if(Flag!=flag){
			if(Flag==true){
				new Thread(new MusicPlayThread()).start();
				flag=Flag;
			}else {
				mediaPlayer.pause();
				mediaPlayer.seekTo(0);
				flag=Flag;
			}
		}
	}
	
	public static void ReleaseIt(){
		mediaPlayer.stop();
		mediaPlayer.release();
	}
}
