package com.example.pingping_game2;

import com.example.pingping_game2.gameargs.GameAgrs;
import com.example.pingping_game2.gametools.MainMapManager;

import android.app.Activity;
import android.os.Bundle;
import android.text.BoringLayout.Metrics;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {

	private DisplayMetrics dMetrics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		dMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dMetrics);
		GameAgrs.InitArgs(dMetrics);
		MainMapManager.Init(GameActivity.this);
		reSetGame();
	}

	public void reSetGame() {
		setContentView(new GameView(GameActivity.this));
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		GameAgrs.Reset();
		this.finish();
	}

}
