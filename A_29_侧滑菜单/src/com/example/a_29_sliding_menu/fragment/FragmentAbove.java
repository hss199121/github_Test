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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.example.a_29_sliding_menu.MainActivity;
import com.example.a_29_sliding_menu.R;

public class FragmentAbove extends Fragment{

	ListView listView;
	Button btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_center, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		listView = (ListView) getView().findViewById(R.id.listView);
		List<String> datas = new ArrayList<String>();
		for (int i = 0; i < 100; i++) {
			datas.add("数据"+i);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,datas);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(listener);
		btn = (Button) getView().findViewById(R.id.button1);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				MainActivity aty = (MainActivity)getActivity();
//				aty.openMenu();
			}
		});
		SeekBar bar = (SeekBar) getView().findViewById(R.id.seekbar_player_playing);
		bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				

			}
		});
	}
	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(getActivity(), parent.getAdapter().getItem(position).toString(), 0).show();
		}
	};
}
