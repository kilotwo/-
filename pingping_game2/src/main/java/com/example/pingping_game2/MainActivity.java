package com.example.pingping_game2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.pingping_game2.gameargs.GameAgrs;
import com.example.pingping_game2.musicTools.MusicPlayThread;
import com.example.pingping_game2.musicTools.VolumsPlayer;
import com.example.pingping_game2.musicTools.musicPlayer;

public class MainActivity extends Activity implements OnClickListener {

	private Button startButton, setmusicButton, overButton,setDifficulty;
	public String level="10";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置为全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置无标题栏
		setContentView(R.layout.activity_main);
		startButton = (Button) this.findViewById(R.id.startButton);
		startButton.setOnClickListener(this);
		setDifficulty = (Button) this.findViewById(R.id.setDifficulty);
		//setDifficulty.setOnClickListener(this);
		setDifficulty.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final EditText editText = new EditText(v.getContext());
				new AlertDialog.Builder(v.getContext()).setTitle("请输入游戏难度\n[2(简单）——6（地狱）]：").setView(editText).setPositiveButton("确定",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								level= editText.getText().toString().trim();
								GameAgrs.Colums_Count=Integer.parseInt(level);
								GameAgrs.Row_Count=Integer.parseInt(level);
								GameAgrs.Total_Count=(GameAgrs.Colums_Count)*(GameAgrs.Colums_Count);
								if(GameAgrs.Colums_Count<=2) {GameView.speed=100;GameAgrs.EpicSize=400;}
								else if(GameAgrs.Colums_Count==3){GameView.speed=80;GameAgrs.EpicSize=300;}
								else if(GameAgrs.Colums_Count==4){GameView.speed=75;GameAgrs.EpicSize=200;}
								else if(GameAgrs.Colums_Count==5){GameView.speed=60;GameAgrs.EpicSize=170;}
								else  {GameView.speed=45;GameAgrs.EpicSize=150;}
							}
						}).setNegativeButton("取消", null).show();
			}
		});



		setmusicButton = (Button) this.findViewById(R.id.setmusicButton);
		setmusicButton.setOnClickListener(this);
		overButton = (Button) this.findViewById(R.id.overButton);
		overButton.setOnClickListener(this);
		musicPlayer.init(MainActivity.this);
		new Thread(new MusicPlayThread()).start();
		VolumsPlayer.init(MainActivity.this);


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		musicPlayer.ReleaseIt();
		VolumsPlayer.ReleaseIt();
		this.finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.startButton:
            Intent intent0=new Intent(MainActivity.this,GameActivity.class);
            startActivity(intent0);
			break;
		case R.id.setmusicButton:
			Intent intent = new Intent(MainActivity.this, SetVolums.class);
			startActivity(intent);
			break;
		case R.id.overButton:
			musicPlayer.ReleaseIt();   
			VolumsPlayer.ReleaseIt();
			this.finish();
			break;
		}
	}

}
