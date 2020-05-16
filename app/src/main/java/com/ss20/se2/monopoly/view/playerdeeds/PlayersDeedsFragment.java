package com.ss20.se2.monopoly.view.playerdeeds;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ss20.se2.monopoly.R;

import static com.esotericsoftware.jsonbeans.JsonValue.ValueType.object;

public class PlayersDeedsFragment extends Fragment{

	private PlayersDeedsViewModel mViewModel;
	private Button close;

	public static PlayersDeedsFragment newInstance(){
		return new PlayersDeedsFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.players_deeds_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this).get(PlayersDeedsViewModel.class);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);

		close = getView().findViewById(R.id.buttonClose);
	}

	public void closeFragment(View view) {
		onDestroy();

	}
}
