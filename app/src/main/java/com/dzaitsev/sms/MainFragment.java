package com.dzaitsev.sms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

	private MyAdapter mAdapter;
	private SmsManager mSmsManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.f_main, container, false);
	}

	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		final ListView listView = (ListView) view.findViewById(R.id.listView);
		mSmsManager = new SmsManager(getActivity());
		mAdapter = new MyAdapter(getActivity(), null);
		listView.setAdapter(mAdapter);

		view.findViewById(R.id.btnInbox).setOnClickListener(this);
		view.findViewById(R.id.btnOutbox).setOnClickListener(this);
		view.findViewById(R.id.btnDraft).setOnClickListener(this);
		view.findViewById(R.id.btnSent).setOnClickListener(this);
	}

	@Override
	public void onClick(final View v) {
		mAdapter.clear();

		switch (v.getId()) {
			case R.id.btnInbox:
				mAdapter.add(mSmsManager.getSmsFromFolder(Sms.Folder.INBOX));
				break;
			case R.id.btnOutbox:
				mAdapter.add(mSmsManager.getSmsFromFolder(Sms.Folder.OUTBOX));
				break;
			case R.id.btnDraft:
				mAdapter.add(mSmsManager.getSmsFromFolder(Sms.Folder.DRAFT));
				break;
			case R.id.btnSent:
				mAdapter.add(mSmsManager.getSmsFromFolder(Sms.Folder.SENT));
				break;
			default:
				break;
		}
	}
}
