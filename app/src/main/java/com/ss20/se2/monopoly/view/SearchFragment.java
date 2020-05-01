package com.ss20.se2.monopoly.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.ss20.se2.monopoly.R;
import com.ss20.se2.monopoly.models.LocallyFoundGame;
import com.ss20.se2.monopoly.network.LocalGamesFinder;
import com.ss20.se2.monopoly.network.NetworkUtilities;
import com.ss20.se2.monopoly.network.OnLocalGamesChangedListener;

import java.util.List;

public class SearchFragment extends Fragment implements View.OnClickListener{

	private ImageButton backBtn;
	private FragmentActivity activity;
	private OnLocalGamesChangedListener onLocalGamesChangedListener;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){
		final View myView = inflater.inflate(R.layout.fragment_search, container, false);
		backBtn = myView.findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		activity = getActivity();
		startSearching(myView);
		return myView;
	}

	public void startSearching(View myView){
		final LinearLayout foundGamesLayout = (LinearLayout) myView.findViewById(R.id.foundGamesLayout);
		LocalGamesFinder.getInstance().startGameSearchInNetwork(this.getContext());
		onLocalGamesChangedListener = new OnLocalGamesChangedListener(){
			@Override
			public void onGamesChanged(final List<LocallyFoundGame> foundGames){
				Log.d(NetworkUtilities.TAG, "Change in LocallyFound games detected");
				activity.runOnUiThread(new Runnable(){
					@Override
					public void run(){
						foundGamesLayout.removeAllViews();
						for (LocallyFoundGame foundGame : foundGames){
							Log.d(NetworkUtilities.TAG, "Found game at: " + foundGame.getAddress() + ":" + foundGame.getPort());
							final String text = "Game: " + foundGame.getAddress() + ":" + foundGame.getPort() + "\n";
							Button joinGame = new Button(activity.getBaseContext());
							joinGame.setText(text);
							foundGamesLayout.addView(joinGame);
						}
					}
				});
			}
		};
		LocalGamesFinder.getInstance().subscribe(onLocalGamesChangedListener);
	}

	@Override
	public void onClick(View v){
		switch (v.getId()){
			case R.id.backBtn:
				LocalGamesFinder.getInstance().stopGameSearchInNetwork(this.getContext());
				LocalGamesFinder.getInstance().unsubscribe(onLocalGamesChangedListener);
				MainActivity.getNavController().navigateUp();
				break;
		}
	}
}
