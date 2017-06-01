package com.example.pingping_game2.musicTools;

import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import com.example.pingping_game2.R;
import android.content.Context;
import android.media.SoundPool;
import android.net.rtp.AudioStream;
import android.provider.MediaStore.Audio;

public class VolumsPlayer {

	private static Context context;
	private static SoundPool soundPool;
	private static Map<Integer, Integer> soundMap;
	private static boolean flag = true;

	public static void init(Context c) {
		// TODO Auto-generated constructor stub
		context = c;
		soundPool = new SoundPool(10, AudioStream.MODE_NORMAL, 0);
		soundMap = new HashMap<Integer, Integer>();
		soundMap.put(R.raw.gang1, soundPool.load(context, R.raw.gang1, 1));
		soundMap.put(R.raw.gang2, soundPool.load(context, R.raw.gang2, 1));
		soundMap.put(R.raw.hit1, soundPool.load(context, R.raw.hit1, 1));

	}

	public static void playit(Integer i) {
		if (!flag) {
			return;
		} else {
			Integer id = soundMap.get(i);
			if (id != null) {
				soundPool.setVolume(soundPool.play(id, 1, 1, 1, 0, 1), (float)0.5, (float)0.5);
			}
		}
	}

	public static void setFlag(boolean Flag) {
		flag = Flag;
	}

	public static boolean getflag(){
		return flag;
	}
	public static void ReleaseIt() {
		soundPool.release();
	}

}
