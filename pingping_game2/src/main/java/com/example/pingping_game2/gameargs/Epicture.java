package com.example.pingping_game2.gameargs;

import com.example.pingping_game2.R;
import com.example.pingping_game2.musicTools.VolumsPlayer;

public class Epicture {

	// 此类用来存储每个方块的动态信息
	public Epicture() {
		// TODO Auto-generated constructor stub
	}

	private int Empty = 0; // 代表此方块为空洞
	private int Top = 13; // 代表此方块地鼠开始钻出
	private int Hited = -9; // 代表此方块地鼠已被打击

	private int Currenty = Empty; // 代表此刻方块的状态

	// 获取现在的方块状态
	public int GetCurrenty() {
		return Currenty;
	}

	// 这个洞开始出地鼠
	public void ToShow() {
		Currenty = Top;
	}

	// 设置这个方块的下一个状态
	public void ToNext() {
		// 当Currenty=0时没有下一个状态，不予以处理
		if (Currenty > 0) {
			Currenty--;
			if (Currenty == 0) { // 如果再地鼠回到洞里之前没打掉就扣血
				GameAgrs.HP--;
			}
		} else if (Currenty < 0) {
			Currenty++;
		}
	}

	// 触发此方块地鼠被打击事件
	public void BeHited() {
		if (Currenty > 0) {
			Currenty = Hited;
			GameAgrs.Grade += GameAgrs.gradeUp;
			VolumsPlayer.playit(R.raw.hit1);
		}
	}
}
