package com.ss20.se2.monopoly.view.dialog;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Railroad;
import com.ss20.se2.monopoly.models.fields.deeds.Street;
import com.ss20.se2.monopoly.view.deed.DeedFragment;
import com.ss20.se2.monopoly.view.deed.DeedFragmentDelegate;
import com.ss20.se2.monopoly.view.deed.railroad.RailroadFragment;

public class DialogContainerFragment extends DialogFragment{

	private Street street;
	private DeedFragmentDelegate delegate;
	private Player player;
	private Railroad railroad;

	public DialogContainerFragment() {
		//Empty because Android needs an constructor without args.
	}

	public static DialogContainerFragment newInstance(){
		return new DialogContainerFragment();
	}

	public void setupViewModel(Street street, Player player, DeedFragmentDelegate delegate){
		this.street = street;
		this.delegate = delegate;
		this.player = player;
	}

	public void setupViewModel(Railroad railroad, Player player, DeedFragmentDelegate delegate){
		this.delegate = delegate;
		this.player = player;
		this.railroad = railroad;
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
		return inflater.inflate(R.layout.dialog_container_fragment, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
		super.onViewCreated(view, savedInstanceState);
		Button buy = view.findViewById(R.id.buttonBuy);
		buy.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				delegate.performAcquiringDeed((street == null ? railroad : street), player);
				dismiss();
			}
		});

		Button cancel = view.findViewById(R.id.buttonCancel);
		cancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				delegate.cancelled();
				dismiss();
			}
		});

		FragmentTransaction transaction = getChildFragmentManager().beginTransaction();

		if (street != null) {
			DeedFragment deedFragment = new DeedFragment();
			transaction.replace(R.id.deedFragmentContainer, deedFragment).commit();
			deedFragment.createViewModel(street);
		} else if (railroad != null) {
			RailroadFragment railroadFragment = new RailroadFragment();
			transaction.replace(R.id.deedFragmentContainer, railroadFragment).commit();
			railroadFragment.createViewModel(railroad);
		}
	}
}
