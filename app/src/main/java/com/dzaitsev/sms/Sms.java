package com.dzaitsev.sms;

import android.database.Cursor;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by d.zaitsev on 11.07.2014.
 */
public class Sms implements BaseColumns {
    private static final String ADDRESS = "address";
    private static final String BODY = "body";
    private static final String READ = "read";
    private static final String DATE = "date";
    private static final String DATE_FORMAT = "dd-MM-yyyy, hh:mm:ss.SSS";

    private String mAddress;
	private String mId;
	private String mMsg;
	private String mReadState;
	private String mTime;

    public static Sms createFrom(final Cursor cursor) {
        final Sms sms = new Sms();
        sms.setId(cursor.getString(cursor.getColumnIndexOrThrow(Sms._ID)));
        sms.setAddress(cursor.getString(cursor.getColumnIndexOrThrow(Sms.ADDRESS)));
        sms.setMsg(cursor.getString(cursor.getColumnIndexOrThrow(Sms.BODY)));
        sms.setReadState(cursor.getString(cursor.getColumnIndex(Sms.READ)));
        final Long millis = Long.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(Sms.DATE)));
        sms.setTime(getDate(millis, DATE_FORMAT));
        return sms;
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

	void setAddress(final String address) {
		mAddress = address;
	}

	void setId(final String id) {
		mId = id;
	}

	void setMsg(final String msg) {
		mMsg = msg;
	}

	void setReadState(final String readState) {
		mReadState = readState;
	}

	void setTime(final String time) {
		mTime = time;
	}

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

        public static Folder findById(final int id) {
            for (final Folder f : values()) {
                if (id == f.ordinal()) {
                    return f;
                }
            }
            return null;
        }
    }

    private static String getDate(final long milliSeconds, final String dateFormat) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return new SimpleDateFormat(dateFormat).format(calendar.getTime());
    }
}
