package com.example.a_29_sliding_menu.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * MenuView和AboveView的父类，主要是创建了一个Scroller对象
 * 以实现从一个地方到另一个地方的平滑滚动
 * @author Administrator
 *
 */
public class SlidingView extends ViewGroup{

	protected Scroller mScroller;
	public SlidingView(Context context) {
		super(context);
		mScroller = new Scroller(getContext());
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).layout(l, t, r, b);
		}
	}
	
	@Override
	public void computeScroll() {
		super.computeScroll();
		if(!mScroller.isFinished()){
			if(mScroller.computeScrollOffset()){
				int oldX = getScrollX();
				int oldY = getScrollY();
				int newX = mScroller.getCurrX();
				int newY = mScroller.getCurrY();
				if(oldX != newX || oldY != newY){
					scrollTo(newX, newY);
				}
				invalidate();
			}else {
				clearChildrenCache();
			}
		}else {
			clearChildrenCache();
		}
	}
	
	private void clearChildrenCache() {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View layout = (View) getChildAt(i);
			layout.setDrawingCacheEnabled(false);
		}
	}
	/**
	 * 平滑的滚动到指定位置
	 * @param destX
	 * @param destY
	 * @param duration
	 */
	protected void smoothScrollTo(int destX,int destY,int duration){
		int dx = destX - getScrollX();
		int dy = destY - getScrollY();
		mScroller.startScroll(getScrollX(), getScrollY(), dx, dy, duration);
		invalidate();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
	}
}
