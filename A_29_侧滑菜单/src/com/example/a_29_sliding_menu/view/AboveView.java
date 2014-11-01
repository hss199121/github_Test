package com.example.a_29_sliding_menu.view;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

public class AboveView extends SlidingView{
	public static final int CONTAINER_ID = 0x2;
	public static final int DURATION = 500;
	private int mTouchSlop;
	private int mMenuWidth = 380;
	private MenuView mMenu;
	/**菜单是否跟着滚动**/
	private boolean mMenuCanSliding;
	public void setmMenuCanSliding(boolean mMenuCanSliding) {
		this.mMenuCanSliding = mMenuCanSliding;
	}
	public void setMenu(MenuView menu) {
		this.mMenu = menu;
	}
	public AboveView(Context context) {
		super(context);
		init();
	}
	private void init() {
		setId(CONTAINER_ID);
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
	}

	private boolean mIsIntercept = false;
	/***
	 * 1.拦截down事件
	 * 	 a)menu关闭的时候 ： 不拦截
	 *   b)menu打开的时候 ：  0~MenuWidth 不拦截    MenuWidth~  拦截
	 * 2:拦截move事件
	 *   a)menu关闭的时候：
	 *   b)
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			firstX = ev.getX();
			if(mState == STATE_MENU_CLOSE){
				mIsIntercept = false;
			}else{
				if(ev.getX() < mMenuWidth){
					mIsIntercept = false;
				}else{
					mIsIntercept = true;
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(mState == STATE_MENU_CLOSE){
				if(ev.getX() - firstX > mTouchSlop){
					mIsIntercept = true;
					lastX = ev.getX();
				}
			}
			break;
		}
		return mIsIntercept;
	}

	private float firstX;
	private float lastX;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(!mIsIntercept && mState == STATE_MENU_OPEN){
			return super.onTouchEvent(event);
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = event.getX();
			log("onTouchEvent---ACTION_DOWN");
			break;
		case MotionEvent.ACTION_MOVE:
			float offset = event.getX() - lastX;
			float scrollX = getScrollX() - offset;
			//右边界处理
			if(scrollX < -mMenuWidth ){
				scrollX = -mMenuWidth;
			}
			//左边界处理
			if(scrollX > 0){
				scrollX = 0;
			}
			//让自己scrollTo
			scrollTo((int) scrollX, getScrollY());
			if(mMenuCanSliding){
				mMenu.scrollTo((int) (mMenuWidth/2+scrollX/2), 0);
			}
			lastX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			log("onTouchEvent--ACTION_UP");
			switch (mState) {
			case STATE_MENU_CLOSE:
				if(getScrollX() < -mMenuWidth*1f/6){
					//打开菜单
					openMenu();
				}else{
					//关闭菜单
					closeMenu();
				}
				break;
			case STATE_MENU_OPEN:
				if(getScrollX() > -mMenuWidth*5f/6){
					//关闭菜单
					closeMenu();
				}else{
					//打开菜单
					openMenu();
				}
				break;
			}
			break;
		}
		return true;
	}

	/**
	 * 关闭菜单
	 */
	public void closeMenu() {
		smoothScrollTo(0, getScrollY(), DURATION);
		if(mMenuCanSliding){
			mMenu.smoothScrollTo(mMenuWidth/2, 0, DURATION);
		}
		mState = STATE_MENU_CLOSE;
		log("closeMenu");
	}
	/**
	 * 打开菜单
	 */
	public void openMenu() {
		smoothScrollTo(-mMenuWidth, getScrollY(), DURATION);
		if(mMenuCanSliding){
			mMenu.smoothScrollTo(0, 0, DURATION);
		}
		mState = STATE_MENU_OPEN;
		log("openMenu");
	}

	public static final int STATE_MENU_CLOSE = 1;
	public static final int STATE_MENU_OPEN = 2;
	private int mState = STATE_MENU_CLOSE;
	
	public void log(Object o){
		Log.d("AboveView", o+"");
	}
	/**
	 * 打开或关闭菜单
	 */
	public void openAndCloseMenu() {
		if(mState == STATE_MENU_OPEN){
			closeMenu();
		}else{
			openMenu();
		}
	}
}
