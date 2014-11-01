package com.example.a_29_sliding_menu.view;

import android.content.Context;

public class MenuView extends SlidingView{
	public static final int CONTAINER_ID = 0x1;
	public MenuView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setId(CONTAINER_ID);
	}

}
