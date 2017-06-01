package com.example.pingping_game2.gameargs;

import android.app.Activity;
import android.util.DisplayMetrics;

//此类用来存储用户参数
public class GameAgrs extends Activity {

	public static int HP = 20; // 用户血量
	public static int Grade = 0; // 用户分数
	public static int gradeUp = 100; // 每打到一个地鼠所追加的分数
	public static int Colums_Count=3 ; // 方块的列数
	public static int Row_Count=3; // 方块的行数
	public static int Total_Count = 9; // 方块的总数
	public static int EpicSize = 300; // 方块的长宽
	public static int Hole_Empty = 0; // 洞为空的参数
	public static int start_X; // 第一个方块的横坐标
	public static int start_Y; // 第一个方块的纵坐标
	public static int text_size; // 字体的大小
	public static int HP_X; // 开始写hp的开始横坐标
	public static int HP_Y; // 开始写Hp的开始纵坐标
	public static int Grade_X; // 开始写Grade的开始横坐标
	public static int Grade_Y; // 开始写Grade的开始纵坐标

	public static void Reset() {
		HP = 20;
		Grade = 0;
	}

	//根据不同的屏幕大小调整参数
	public static void InitArgs(DisplayMetrics dMetrics) {

		int width = dMetrics.widthPixels;
		int Height = dMetrics.heightPixels;
		if (width == 320 && Height == 480) {
			//EpicSize = 80;
			start_X = 35;
			start_Y = 76;
			text_size = 22;
			HP_X = 20;
			HP_Y = 20;
			Grade_X = 20;
			Grade_Y = 50;
		} else if (width == 480 && Height == 854) {
			//EpicSize = 120;
			start_X = 40;
			start_Y = 170;
			text_size = 40;
			HP_X = 40;
			HP_Y = 40;
			Grade_X = 40;
			Grade_Y = 90;
		}else{
			//EpicSize = 100;
			start_X = 40;
			start_Y = 270;
			text_size = 40;
			HP_X = 40;
			HP_Y = 40;
			Grade_X = 40;
			Grade_Y = 90;
		}
	}


}
