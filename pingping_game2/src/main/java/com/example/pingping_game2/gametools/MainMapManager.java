package com.example.pingping_game2.gametools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.example.pingping_game2.R;
import com.example.pingping_game2.gameargs.GameAgrs;

import java.util.HashMap;
import java.util.Map;

public class MainMapManager {

	public MainMapManager() {
		// TODO Auto-generated constructor stub
	}

	public static Map<Integer, Bitmap> mainMap; // 游戏的地图信息
	private static Context context;

	public static Bitmap getBitmap(int i) {
		return mainMap.get(i);
	}

	public static void Init(Context c) {
		context = c;
		// 将游戏对应的地图信息都存储到mainMap中，方便取出
		mainMap = new HashMap<Integer, Bitmap>();
		mainMap.put(new Integer(13),
				getbitmap(context.getResources(), R.drawable.show1));
		mainMap.put(new Integer(12),
				getbitmap(context.getResources(), R.drawable.show2));
		mainMap.put(new Integer(11),
				getbitmap(context.getResources(), R.drawable.show3));
		mainMap.put(new Integer(10),
				getbitmap(context.getResources(), R.drawable.show4));
		mainMap.put(new Integer(9),
				getbitmap(context.getResources(), R.drawable.show5));
		mainMap.put(new Integer(8),
				getbitmap(context.getResources(), R.drawable.show6));
		mainMap.put(new Integer(7),
				getbitmap(context.getResources(), R.drawable.show6));
		mainMap.put(new Integer(6),
				getbitmap(context.getResources(), R.drawable.show6));
		mainMap.put(new Integer(5),
				getbitmap(context.getResources(), R.drawable.show5));
		mainMap.put(new Integer(4),
				getbitmap(context.getResources(), R.drawable.show4));
		mainMap.put(new Integer(3),
				getbitmap(context.getResources(), R.drawable.show3));
		mainMap.put(new Integer(2),
				getbitmap(context.getResources(), R.drawable.show2));
		mainMap.put(new Integer(1),
				getbitmap(context.getResources(), R.drawable.show1));

		mainMap.put(new Integer(0),
				getbitmap(context.getResources(), R.drawable.emptyhole));

		mainMap.put(new Integer(-9),
				getbitmap(context.getResources(), R.drawable.hit));
		mainMap.put(new Integer(-8),
				getbitmap(context.getResources(), R.drawable.hit));
		mainMap.put(new Integer(-7),
				getbitmap(context.getResources(), R.drawable.hit));
		mainMap.put(new Integer(-6),
				getbitmap(context.getResources(), R.drawable.hit));
		mainMap.put(new Integer(-5),
				getbitmap(context.getResources(), R.drawable.show5));
		mainMap.put(new Integer(-4),
				getbitmap(context.getResources(), R.drawable.show4));
		mainMap.put(new Integer(-3),
				getbitmap(context.getResources(), R.drawable.show3));
		mainMap.put(new Integer(-2),
				getbitmap(context.getResources(), R.drawable.show2));
		mainMap.put(new Integer(-1),
				getbitmap(context.getResources(), R.drawable.show1));

	}

	// 将资源中的图片进行大小处理后返回bitmap
	private static Bitmap getbitmap(Resources res, int id) {
		int size=GameAgrs.EpicSize;
		Drawable image = res.getDrawable(id); // 从资源中取出image
		Bitmap bitmap = Bitmap.createBitmap(size, size, Config.ARGB_8888);
		Rect rect = new Rect(0, 0, size, size);
		image.setBounds(rect); // 将其以一定比例画到bitmap画板上
		Canvas canvas = new Canvas(bitmap);
		image.draw(canvas);

		return bitmap;
	}

}
