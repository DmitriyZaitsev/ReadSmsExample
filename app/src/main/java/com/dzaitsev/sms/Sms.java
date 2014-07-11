package com.dzaitsev.sms;

import android.provider.BaseColumns;
import android.provider.Telephony;

/**
 * Created by d.zaitsev on 11.07.2014.
 */
public class Sms implements BaseColumns, Telephony.TextBasedSmsColumns {
	private String mAddress;
	private String mId;
	private String mMsg;
	private String mReadState;
	private String mTime;


	public enum Folder {
		INBOX("inbox"),
		SENT("sent"),
		DRAFT("draft"),
		OUTBOX("outbox");

		private final String mTable;

		private Folder(final String table) {
			mTable = table;
		}

		public String getTable() {
			return mTable;
		}
	}

	public String getAddress() {
		return mAddress;
	}

	public String getId() {
		return mId;
	}

	public String getMsg() {
		return mMsg;
	}

	public String getReadState() {
		return mReadState;
	}

	public String getTime() {
		return mTime;
	}

	public void setAddress(final String address) {
		mAddress = address;
	}

	public void setId(final String id) {
		mId = id;
	}

	public void setMsg(final String msg) {
		mMsg = msg;
	}

	public void setReadState(final String readState) {
		mReadState = readState;
	}

	public void setTime(final String time) {
		mTime = time;
	}
}
