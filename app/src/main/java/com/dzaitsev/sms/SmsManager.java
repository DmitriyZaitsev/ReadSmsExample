package com.dzaitsev.sms;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by d.zaitsev on 11.07.2014.
 */
public class SmsManager {
	private final Activity mContext;

	public SmsManager(Activity activity) {
		mContext = activity;
	}

	public List<Sms> getSmsFromFolder(Sms.Folder folder) {
		List<Sms> smsList = new LinkedList<Sms>();
		Uri message = Uri.parse("content://sms/"+folder.getTable());
		ContentResolver contentResolver = mContext.getContentResolver();

		Cursor cursor = contentResolver.query(message, null, null, null, null);
		mContext.startManagingCursor(cursor);
		int totalSMS = cursor.getCount();

		if (cursor.moveToFirst()) {
			for (int i = 0; i < totalSMS; i++) {
				final Sms sms = new Sms();
				sms.setId(cursor.getString(cursor.getColumnIndexOrThrow(Sms._ID)));
				sms.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(Sms.ADDRESS)));
				sms.setMsg(cursor.getString(cursor.getColumnIndexOrThrow(Sms.BODY)));
				sms.setReadState(cursor.getString(cursor.getColumnIndex(Sms.READ)));
				sms.setTime(cursor.getString(cursor.getColumnIndexOrThrow(Sms.DATE)));

				smsList.add(sms);
				cursor.moveToNext();
			}
		}

		cursor.close();

		return smsList;
	}
}
