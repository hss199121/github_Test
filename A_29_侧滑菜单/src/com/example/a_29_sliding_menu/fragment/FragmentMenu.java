package com.example.a_29_sliding_menu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a_29_sliding_menu.FragmentOther;
import com.example.a_29_sliding_menu.MainActivity;
import com.example.a_29_sliding_menu.R;

public class FragmentMenu extends Fragment{
	ListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_left, null);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		listView = (ListView) getView().findViewById(R.id.listView);
		List<String> datas = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			datas.add("---菜单数据"+i+"---菜单数据");
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,datas);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(listener);
	}
	private OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getActivity(), parent.getAdapter().getItem(position).toString(), 0).show();
			switchContent(new FragmentOther());
		}
	};
	
	private void switchContent(Fragment fragment){
		MainActivity aty = (MainActivity) getActivity();
		aty.switchContent(fragment);
	}
}
