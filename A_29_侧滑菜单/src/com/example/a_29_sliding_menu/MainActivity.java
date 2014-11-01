package com.example.a_29_sliding_menu;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a_29_sliding_menu.fragment.FragmentAbove;
import com.example.a_29_sliding_menu.fragment.FragmentMenu;
import com.example.a_29_sliding_menu.view.AboveView;
import com.example.a_29_sliding_menu.view.MenuView;
import com.example.a_29_sliding_menu.view.RootView;

public class MainActivity extends FragmentActivity {
	
	private RootView mRootView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mRootView = new RootView(this);
		setContentView(mRootView);
		
		FragmentManager fManager = getSupportFragmentManager();
		FragmentTransaction ft = fManager.beginTransaction();
		ft.add(MenuView.CONTAINER_ID, new FragmentMenu(), "FragmentMenu");
		ft.add(AboveView.CONTAINER_ID, new FragmentAbove(), "FragmentAbove");
		//提交额
		ft.commit();
		
		mRootView.setMenuCanSliding(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mRootView.openOrCloseMenu();
		return false;
	}

	/**
	 * 切换AboveView显示的Fragment
	 * @param fragment
	 */
	public void switchContent(Fragment fragment) {
		FragmentManager fManager = getSupportFragmentManager();
//		List<Fragment> fragments = fManager.getFragments();
		
//		if(!fragments.contains(fragment)){
			FragmentTransaction ft = fManager.beginTransaction();
			ft.replace(AboveView.CONTAINER_ID, fragment);
			ft.commit();
//			Toast.makeText(getApplicationContext(), "切换Fragment", 0).show();
//		}else{
//			Toast.makeText(getApplicationContext(), "此时正在此界面", 0).show();
//		}
		mRootView.closeMenu();
	}
	
}
