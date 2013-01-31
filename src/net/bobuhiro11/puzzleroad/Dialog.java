package net.bobuhiro11.puzzleroad;

import net.bobuhiro11.puzzleroadconsole.Puzzle;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

/*
 * パズル完成時のメッセージです．
 */
public class Dialog {
	Rect src,dst;
	Bitmap bmp;
	MainView mainView;
	
	public Dialog(Context context,MainView mainView,int w,int h){
		this.mainView = mainView;
		this.src = new Rect(0,0,700,1200);
		this.dst = new Rect(0,0,w,h);
		
		Resources r = context.getResources();
        bmp = BitmapFactory.decodeResource(r, R.drawable.complete_back);
	}
	
	public void draw(Canvas canvas){
		canvas.drawBitmap(bmp, src, dst, null);
	}

	/**
	 * @param event
	 * @param n　盤面の大きさ
	 */
	public void touch(MotionEvent event,int n){
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			this.mainView.status = Status.playing;
			
			//ゲーム数カウントアップ
			mainView.gameCount.up();
			//パズルを初期化
			mainView.playPuzzle.puzzle.init(n+2,1);
			//スタート，ゴールオブジェクト更新
			mainView.startObject.setPoint(mainView.playPuzzle.puzzle.start);
			mainView.goalObject.setPoint(mainView.playPuzzle.puzzle.goal);
		}
	}
}
