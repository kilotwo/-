package com.example.pingping_game2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.example.pingping_game2.gameargs.Epicture;
import com.example.pingping_game2.gameargs.GameAgrs;
import com.example.pingping_game2.gametools.MainMapManager;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameView extends View {
	public static int speed = 100; // 刷新速度
	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		GameView.this.post(UpdatePaint); // 刷新画面线程
		GameView.this.postDelayed(setMouse, 1000); // 增加地鼠的线程,1s后执行
	}

	int start_X = GameAgrs.start_X; // 开始画方块的X坐标
	int start_Y = GameAgrs.start_Y; // 开始画方块的Y坐标
	private Random random = new Random(); // 定义一个随机函数

	// 初始化每块方块的信息
	private List<Epicture> mainList = new ArrayList<Epicture>(
			GameAgrs.Total_Count);
	{
		for (int i = 0; i < GameAgrs.Total_Count; i++) {
			mainList.add(new Epicture());
		}
	}

	Runnable UpdatePaint = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			GameView.this.invalidate();
			GameView.this.postDelayed(this, speed); // 每100ms刷新一次
		}
	};

	Runnable setMouse = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			LinkedList<Epicture> tempList = new LinkedList<Epicture>();
			for (int i = 0; i < mainList.size(); i++) {
				Epicture epicture = mainList.get(i);
				if (epicture.GetCurrenty() == GameAgrs.Hole_Empty) {
					tempList.add(epicture);
				}
			}
			int tempSize = tempList.size();
			if (tempSize == 1) {
				tempList.poll().ToShow();
			} else if (tempSize > 1) {
				// 随机出一个或者两个地鼠
				for (int i = 0; i < random.nextInt(2) + 1; i++) {
					tempList.remove(random.nextInt(tempList.size())).ToShow();
				}
				GameView.this.invalidate();
			}
			GameView.this.postDelayed(this, 800 - GameAgrs.Grade / 20);
		}
	};

	private void DrawMessage(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setTextSize(GameAgrs.text_size);
		canvas.drawText("HP：  " + GameAgrs.HP, GameAgrs.HP_X, GameAgrs.HP_Y,
				paint);
		canvas.drawText("\nGrade:  " + GameAgrs.Grade, GameAgrs.Grade_X,
				GameAgrs.Grade_Y, paint);
	}

	// 游戏结束处理
	private void DoGameOver() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		if(GameAgrs.Grade<2500)
			builder.setTitle("继续努力O(∩_∩)O");
		else if(GameAgrs.Grade<3500)
			builder.setTitle("那你很棒棒呦（。＾▽＾）");
		else
			builder.setTitle("哇!你坠棒(๑•̀ㅂ•́)و✧");

		builder.setMessage("得分:" + GameAgrs.Grade + ",是否重新开始游戏?");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				GameAgrs.Reset(); // 用户信息重置
				GameActivity gameActivity = (GameActivity) getContext();
				gameActivity.reSetGame(); // 按确定则重新开始加载
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				GameAgrs.Reset(); // 用户信息重置
				GameActivity gameActivity = (GameActivity) getContext();
				gameActivity.finish();
			}
		});
		builder.setCancelable(false);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (GameAgrs.HP <= 0) {
			getHandler().removeCallbacks(UpdatePaint);
			getHandler().removeCallbacks(setMouse);
			DoGameOver();
			return;
		}
		canvas.drawColor(Color.WHITE); // 将背景设置为白色

		DrawMessage(canvas); // 显示用户信息

		for (int i = 0; i < mainList.size(); i++) {
			Epicture epicture = mainList.get(i);

			int x = i % GameAgrs.Colums_Count; // 获得所在列
			int y = i / GameAgrs.Colums_Count; // 获得所在行

			canvas.drawBitmap(MainMapManager.getBitmap(epicture.GetCurrenty()),
					start_X + x * GameAgrs.EpicSize, start_Y + y
							* GameAgrs.EpicSize, null);

			epicture.ToNext(); // 进入下一个状态
		}
	}

	// 触摸事件
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() != MotionEvent.ACTION_DOWN) {
			return true;
		}
		float x = event.getX();
		float y = event.getY();
		int X = (int) ((x - start_X) / GameAgrs.EpicSize);
		int Y = (int) ((y - start_Y) / GameAgrs.EpicSize);
		if (X < 0 || Y < 0 || X >= 3 || Y >= 4) { // 如果打击不在方块范围内则不响应
			return true;
		}
		int num = Y * GameAgrs.Colums_Count + X;
		Epicture epicture = mainList.get(num);
		epicture.BeHited();
		return true;
	}

}
