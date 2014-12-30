package com.dzaitsev.sms;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by dmitriyzaitsev on 12/30/14.
 */
public class MyAdapter extends CursorAdapter implements LoaderManager.LoaderCallbacks<Cursor> {

    public static MyAdapter newInstance(final Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return new MyAdapter(context, null, false);
        } else {
            return new MyAdapter(context, null, 0);
        }
    }

    private final Context mContext;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private MyAdapter(final Context context, final Cursor c, final int flags) {
        super(context, c, flags);
        mContext = context;
    }

    private MyAdapter(final Context context, final Cursor c, final boolean autoRequery) {
        super(context, c, autoRequery);
        mContext = context;
    }

    @Override
    public View newView(final Context context, final Cursor cursor, final ViewGroup parent) {
        final View view = View.inflate(mContext, R.layout.list_tem, null);
        final ViewHolder holder = new ViewHolder();

        holder.address = (TextView) view.findViewById(R.id.address);
        holder.id = (TextView) view.findViewById(R.id.id);
        holder.msg = (TextView) view.findViewById(R.id.msg);
        holder.readState = (TextView) view.findViewById(R.id.readState);
        holder.time = (TextView) view.findViewById(R.id.time);

        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        final Sms sms = Sms.createFrom(cursor);
        final ViewHolder holder = (ViewHolder) view.getTag();
        holder.id.setText(sms.getId());
        holder.address.setText(sms.getAddress());
        holder.msg.setText(sms.getMsg());
        holder.readState.setText(sms.getReadState());
        holder.time.setText(sms.getTime());
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        final Sms.Folder folder = Sms.Folder.findById(id);
        return new CursorLoader(mContext, Uri.parse("content://sms/" + folder.getTable()), null, null, null, null);
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        changeCursor(data);
        notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
    }

    public void setFolder(final Sms.Folder folder) {
        final int id = folder.ordinal();
        final LoaderManager manager = ((FragmentActivity) mContext).getSupportLoaderManager();
        final Loader<Object> loader = manager.getLoader(id);

        if (loader == null) {
            manager.initLoader(id, null, this);
        } else {
            manager.restartLoader(id, null, this);
        }
    }

    private static class ViewHolder {
        TextView address, id, msg, readState, time;
    }
}
