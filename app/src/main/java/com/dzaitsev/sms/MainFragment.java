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
@SuppressWarnings("WeakerAccess")
public class MainFragment extends Fragment implements View.OnClickListener {
	private MyAdapter mAdapter;

	@Override
	public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
		return inflater.inflate(R.layout.f_main, container, false);
	}

	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		final ListView listView = (ListView) view.findViewById(R.id.listView);
		mAdapter = MyAdapter.newInstance(getActivity());
		listView.setAdapter(mAdapter);

		view.findViewById(R.id.btnInbox).setOnClickListener(this);
		view.findViewById(R.id.btnOutbox).setOnClickListener(this);
		view.findViewById(R.id.btnDraft).setOnClickListener(this);
		view.findViewById(R.id.btnSent).setOnClickListener(this);
	}

	@Override
	public void onClick(final View v) {
		switch (v.getId()) {
			case R.id.btnInbox:
				mAdapter.setFolder(Sms.Folder.INBOX);
				break;
			case R.id.btnOutbox:
				mAdapter.setFolder(Sms.Folder.OUTBOX);
				break;
			case R.id.btnDraft:
				mAdapter.setFolder(Sms.Folder.DRAFT);
				break;
			case R.id.btnSent:
				mAdapter.setFolder(Sms.Folder.SENT);
				break;
			default:
				break;
		}
	}
}
