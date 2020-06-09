package com.ss20.se2.monopoly.view.customElements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ss20.se2.monopoly.R;

public class SpinnerAdapter extends ArrayAdapter<String>{

	private Context ctx;
	private String[] contentArray;
	private Integer[] imageArray;

	public SpinnerAdapter(Context context, int resource, String[] objects, Integer[] imageArray){
		super(context, R.layout.spinner_value_layout, R.id.spinnerTextView, objects);
		this.ctx = context;
		this.contentArray = objects;
		this.imageArray = imageArray;
	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.spinner_value_layout, parent, false);
		ImageView imageView = (ImageView) row.findViewById(R.id.spinnerImages);
		//imageView.setImageResource(imageArray[position]);
		return row;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.spinner_value_layout, parent, false);
		ImageView imageView = (ImageView) row.findViewById(R.id.spinnerImages);
		//imageView.setImageResource(imageArray[position]);
		return row;
	}
}
