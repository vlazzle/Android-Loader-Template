package com.twitter.loader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StringLoader extends AsyncTaskLoader<List<String>> {
    private static final int TIMES = 15;
    private List<String> mCache;

    public StringLoader(Context context) {
        super(context);
        Toast.makeText(context, "Loader created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<String> loadInBackground() {
        ArrayList<String> list = new ArrayList<>();
        for (char c = 'A'; c <= 'z'; c++) {
            final StringBuilder buff = new StringBuilder();
            for (int i = 0; i < TIMES; i++) {
                buff.append(c);
            }
            buff.append(" ");
            for (int i = 0; i < TIMES; i++) {
                buff.append(c);
            }
            list.add(buff.toString());
        }
        return list;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mCache == null) {
            forceLoad();
        } else {
            deliverResult(mCache);
        }
    }

    @Override
    public void deliverResult(@NonNull List<String> data) {
        super.deliverResult(data);
        mCache = data;
    }

    @Override
    public void reset() {
        super.reset();
        mCache = null;
    }
}
