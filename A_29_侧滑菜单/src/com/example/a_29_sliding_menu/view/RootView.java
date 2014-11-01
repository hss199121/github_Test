package com.example.a_29_sliding_menu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
/**
 * 这个是根容器，里面有一个菜单容器和主视图容器
 * @author Administrator
 *
 */
public class RootView extends RelativeLayout{
	private MenuView menuView;
	private AboveView aboveView;
	private int menuWidth = 380;
	public RootView(Context context) {
		super(context);
		init();
	}
	public RootView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		addMenu();
		addAbove();
	}
	/**
	 * 添加菜单视图
	 */
	private void addMenu(){
		menuView = new MenuView(getContext());
		LayoutParams params = new LayoutParams(menuWidth, LayoutParams.MATCH_PARENT);
		addView(menuView, params);
	}
	/**
	 * 添加顶部主视图
	 */
	private void addAbove(){
		aboveView = new AboveView(getContext());
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		aboveView.setMenu(menuView);
		addView(aboveView,params);
	}
	/**
	 * 设置菜单view是否滚动
	 * @param menuCanSliding
	 */
	public void setMenuCanSliding(boolean menuCanSliding){
		aboveView.setmMenuCanSliding(menuCanSliding);
		if(menuCanSliding){
			menuView.scrollTo(menuWidth/2, 0);
		}
	}
	public void openOrCloseMenu() {
		aboveView.openAndCloseMenu();
	}
	public void closeMenu() {
		aboveView.closeMenu();
	}
}
