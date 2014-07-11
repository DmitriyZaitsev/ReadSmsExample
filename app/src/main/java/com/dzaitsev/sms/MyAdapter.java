package com.dzaitsev.sms;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d.zaitsev on 11.07.2014.
 */
public class MyAdapter extends BaseAdapter {
	private final List<Sms> mSmsList;
	private final Context mContext;

	public MyAdapter(final Context context, final List<Sms> smsList) {
		mContext = context;
		mSmsList = (smsList == null ? new ArrayList<Sms>(0) : smsList);
	}

	@Override
	public boolean isEmpty() {
		return mSmsList.isEmpty();
	}

	@Override
	public int getCount() {
		return mSmsList.size();
	}

	@Override
	public Sms getItem(final int position) {
		return mSmsList.get(position);
	}

	@Override
	public long getItemId(final int position) {
		return position;
	}

	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		View view;
		ViewHolder viewHolder;

		if (convertView == null) {
			view = View.inflate(mContext, R.layout.layout, null);
			viewHolder = new ViewHolder();

			viewHolder.address = (TextView) view.findViewById(R.id.address);
			viewHolder.id = (TextView) view.findViewById(R.id.id);
			viewHolder.msg = (TextView) view.findViewById(R.id.msg);
			viewHolder.readState = (TextView) view.findViewById(R.id.readState);
			viewHolder.time = (TextView) view.findViewById(R.id.time);

			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}

		final Sms sms = mSmsList.get(position);

		viewHolder.address.setText(sms.getAddress());
		viewHolder.id.setText(sms.getId());
		viewHolder.msg.setText(sms.getMsg());
		viewHolder.readState.setText(sms.getReadState());
		viewHolder.time.setText(sms.getTime());

		return view;
	}

	public void add(final List<Sms> smsList) {
		mSmsList.addAll(smsList);
		notifyDataSetChanged();
	}

	public void clear() {
		mSmsList.clear();
		notifyDataSetChanged();
	}

	private static class ViewHolder {
		TextView address, id, msg, readState, time;
	}
}
