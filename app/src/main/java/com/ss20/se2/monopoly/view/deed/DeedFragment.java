package com.ss20.se2.monopoly.view.deed;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ss20.se2.monopoly.R;

public class DeedFragment extends Fragment{

	private DeedViewModel mViewModel;

	public static DeedFragment newInstance(){
		return new DeedFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.deed_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(this).get(DeedViewModel.class);
		// TODO: Use the ViewModel
	}
}
